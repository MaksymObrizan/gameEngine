package entities;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import collision.AABB;
import models.TexturedModel;
import terrain.Terrain;

public abstract class Entity extends GameObject{

	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	
	private int textureIndex = 0;
	
	public AABB collider;
	public List<AABB> innerColider;
	private boolean solid;
	private boolean movable;
	
	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(position, model);
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		

		this.collider = new AABB(position, new Vector3f(scale,scale,scale));
		this.collider.setCenter(position);
		this.solid = false;
		this.movable = false;
		
	}
	
	public Entity(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(position);
		this.textureIndex = index;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		

		this.collider = new AABB(position, new Vector3f(1,1,1));
		this.solid = false;
	}
	
	public Entity(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale, boolean solid) {
		super(position);
		this.textureIndex = index;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		

		this.collider = new AABB(position, new Vector3f(1,1,1));
		this.solid = solid;
	}
	
	
	public float getTextureXOffset()
	{
		int column = textureIndex % getRenderComponent().getTexture().getNumberOfRows();
		return (float)column/(float)getRenderComponent().getTexture().getNumberOfRows();
	}
	
	public float getTextureYOffset()
	{
		int row = textureIndex / getRenderComponent().getTexture().getNumberOfRows();
		return (float)row/(float)getRenderComponent().getTexture().getNumberOfRows();
	}

	public void increasePosition(float dx, float dy, float dz)
	{
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz)
	{
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}
	
	public TexturedModel getModel() {
		return getRenderComponent();
	}
	public void setModel(TexturedModel model) {
		this.setRenderComponent(model);
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public float getRotX() {
		return rotX;
	}
	public void setRotX(float rotX) {
		this.rotX = rotX;
	}
	public float getRotY() {
		return rotY;
	}
	public void setRotY(float rotY) {
		this.rotY = rotY;
	}
	public float getRotZ() {
		return rotZ;
	}
	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	public abstract void update();
	
	
	
	public AABB getCollider() {
		return collider;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean isSolid(){
		return solid;
	}

	public void work(Terrain terrain)
	{
		
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}
	
	
}
