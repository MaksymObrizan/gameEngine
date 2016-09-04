package objects;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import managers.GameObjectManager;
import models.TexturedModel;
import terrain.Terrain;
import toolbox.Maths;

public class House extends Entity{

	public House(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, index, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
		setMovable(false);
	}
	
	

	public House(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, Terrain terrain) {
		super(model, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
		float x = Maths.getNextFloat() * Terrain.SIZE/10 *-1;
		float z = Maths.getNextFloat() * Terrain.SIZE/10 *-1;
		float y = terrain.getHeightOfTerrain(x, z);
		super.setPosition(new Vector3f(x, y, z));
		collider.setCenter(getPosition());
		collider.setHalfExtent(new Vector3f(5,3,5));
		
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
