package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;
import terrain.Terrain;

public class Player extends Entity{

	private static final float RUN_SPEED = 10;
	private static final float TURN_SPEED = 25;
	private static final float SIDE_SPEED = 5; 
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30;
	
	private float currentSpeed = 0;
	private float currentSideSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	
	private boolean isInAir = false;
	
	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
		setSolid(true);
		setMovable(true);
	}

	
	
	@Override
	public void update() {
		
	}



	public void move(Terrain terrain)
	{
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed*DisplayManager.getFrameTimeSeconds(), 0);
		float distanceForward = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float distanceSide = currentSideSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float)(distanceForward * Math.sin(Math.toRadians(super.getRotY())) - distanceSide * Math.cos(Math.toRadians(super.getRotY())) );
		float dz = (float)(distanceForward * Math.cos(Math.toRadians(super.getRotY())) + distanceSide * Math.sin(Math.toRadians(super.getRotY())) );
		super.increasePosition(dx, 0, dz);
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if(super.getPosition().y < terrainHeight)
		{
			upwardsSpeed = 0;
			super.getPosition().y = terrainHeight;
			isInAir = false;			
		}
	}
	
	private void jump()
	{
		if(!isInAir)
		{
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
	}
	
	private void checkInputs()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			{
				this.currentSpeed = RUN_SPEED * 10;
			}else{
				this.currentSpeed = RUN_SPEED;
			}
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			this.currentSpeed = -RUN_SPEED;
		}else{
			this.currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			this.currentSideSpeed = SIDE_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			this.currentSideSpeed = -SIDE_SPEED;
		}else{
			this.currentSideSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			jump();
		}
		
		if(Mouse.isButtonDown(0))
		{
			this.currentTurnSpeed = Mouse.getDX() * -TURN_SPEED;
			
		}
	}
}
