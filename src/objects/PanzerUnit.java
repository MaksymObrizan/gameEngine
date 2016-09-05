package objects;


import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.TexturedModel;
import renderEngine.DisplayManager;
import terrain.Terrain;
import toolbox.Maths;

public class PanzerUnit extends Entity{

	private float RUN_SPEED = 10;
	private static final float TURN_SPEED = 30;
	private static final float GRAVITY = -50;
	
	private final float DISTANCE_TO_TARGET = 5;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	
	private Vector2f target;
	private CubeState state;
//	Random rand;
	
	
	
	public PanzerUnit(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ,
			float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		
		float x = Maths.getNextFloat() * Terrain.SIZE/10 *-1;
		float z = Maths.getNextFloat() * Terrain.SIZE/10 *-1;
		super.setPosition(new Vector3f(x, position.y, z));
		collider.setCenter(getPosition());
	
		state = CubeState.idle;
		RUN_SPEED = Maths.getPseudoGaussianRand()*RUN_SPEED;
		System.out.println("Run speed = " + RUN_SPEED);
		setSolid(true);
		setMovable(true);
	}
	
	public void work(Terrain terrain)
	{
		gravitation(terrain);
		switch(state)
		{
			case idle:
				idle();
				break;
			case walkToTarget:
				walkToTarget();
				break;
			case turnToTarget:
				turnToTarget();
				break;
			default:
				state = CubeState.idle;
				break;
		}
		

	}
	
	void idle()
	{
		float x = Maths.getNextFloat() * Terrain.SIZE/4 *-1;
		float z = Maths.getNextFloat() * Terrain.SIZE/4 *-1;
		target = new Vector2f(x,z);
	
		state = CubeState.turnToTarget;
	}
	
	void walkToTarget()
	{			
		float distance = Maths.distanceVector2f(this.getPosition(), target);
		if(distance > DISTANCE_TO_TARGET){
			currentSpeed = RUN_SPEED;
			float distanceCurr = currentSpeed * DisplayManager.getFrameTimeSeconds();
			float dx = (float)(distanceCurr * Math.sin(Math.toRadians(super.getRotY())));
			float dz = (float)(distanceCurr * Math.cos(Math.toRadians(super.getRotY())));
			super.increasePosition(dx, 0, dz);
		}else{
			currentSpeed = 0;
			state = CubeState.idle;
		}
	}
	
	void turnToTarget()
	{
		currentSpeed = 0;
		Vector2f pos = new Vector2f(this.getPosition().getX(),this.getPosition().getZ());
		
		//вектор напрямку на ціль
		Vector2f vectorToTarget = new Vector2f(target.getX() - pos.getX(), target.getY() - pos.getY());
		//вектор "погляду"
		Vector2f vectorFacing = new Vector2f( (float)Math.sin(Math.toRadians(super.getRotY())), (float)Math.cos(Math.toRadians(getRotY())) );
		
		
		float theta = Maths.getAngle(vectorToTarget, vectorFacing);
		
		
			if(theta > 0.01f ) {
				if(theta > 360) theta %= 360;
				if(theta < 180){
					currentTurnSpeed = -TURN_SPEED;
				}else{
					currentTurnSpeed = TURN_SPEED;
				}
				
			}else{
				currentTurnSpeed = 0;
				state = CubeState.walkToTarget;
			}
			super.increaseRotation(0, currentTurnSpeed*DisplayManager.getFrameTimeSeconds(), 0);
	}
	
	
	enum CubeState
	{
		idle,
		walkToTarget,
		turnToTarget
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	private void gravitation(Terrain terrain)
	{
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if(super.getPosition().y < terrainHeight)
		{
			super.getPosition().y = terrainHeight;
		}
	}
	
	
}
