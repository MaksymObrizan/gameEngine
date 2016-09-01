package collision;

import org.lwjgl.util.vector.Vector3f;

public class Collision {
	public Vector3f distance;
	public boolean isIntersecting;
	
	
	public Collision(Vector3f distance, boolean isIntersecting) {
		this.distance = distance;
		this.isIntersecting = isIntersecting;
	}
	
	
}
