package objects;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import collision.AABB;
import entities.Entity;
import managers.GameObjectManager;
import models.TexturedModel;
import terrain.Terrain;
import toolbox.Maths;

public class Barrack extends Entity{

	public Barrack(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, index, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
		setMovable(false);
	}
	
	

	public Barrack(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, Terrain terrain) {
		super(model, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
		float x = Maths.getNextFloat() * Terrain.SIZE/10 *-1;
		float z = Maths.getNextFloat() * Terrain.SIZE/10 *-1;
		float y = terrain.getHeightOfTerrain(x, z);
		super.setPosition(new Vector3f(x, y, z));
		collider.setCenter(getPosition());
		collider.setHalfExtent(new Vector3f(5,1,5));
		
		innerColider = new ArrayList<AABB>();
		innerColider.add(new AABB(new Vector3f(getPosition().x-3,getPosition().y,getPosition().z), new Vector3f(1,1,2)));
		innerColider.add(new AABB(new Vector3f(getPosition().x,getPosition().y,getPosition().z+3), new Vector3f(2,1,1)));
		innerColider.add(new AABB(new Vector3f(getPosition().x-3,getPosition().y+1,getPosition().z+3), new Vector3f(1,2,1)));
		innerColider.add(new AABB(new Vector3f(getPosition().x+3,getPosition().y+1,getPosition().z+3), new Vector3f(1,2,1)));
		innerColider.add(new AABB(new Vector3f(getPosition().x+3,getPosition().y,getPosition().z), new Vector3f(1,1,2)));
		
		setSolid(true);
		setMovable(false);
	}



	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void work(Terrain terrain) {
		// TODO Auto-generated method stub
	}


}
