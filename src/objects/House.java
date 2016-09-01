package objects;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.TexturedModel;
import terrain.Terrain;
import toolbox.Maths;

public class House extends Entity{

	public House(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, index, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
		float x = Maths.getNextFloat() * Terrain.SIZE/10 *-1;
		float z = Maths.getNextFloat() * Terrain.SIZE/10 *-1;
		super.setPosition(new Vector3f(x, position.y, z));
		collider.setCenter(getPosition());
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
		super.work(terrain);
	}


}
