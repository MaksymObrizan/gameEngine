package collision;

import org.lwjgl.util.vector.Vector3f;

public class AABB {
	private Vector3f center, halfExtent;

	
	
	public AABB(Vector3f center, Vector3f halfExtent) {
		this.center = center;
		this.halfExtent = halfExtent;
	}
	
	public Collision getCollision(AABB box2)
	{
		Vector3f distance  = box2.center.sub(center, box2.center, new Vector3f());
		distance.x = (float)Math.abs(distance.x);
		distance.y = (float)Math.abs(distance.y);
		distance.z = (float)Math.abs(distance.z);
		
		distance.sub(distance, halfExtent.add(halfExtent, box2.halfExtent, new Vector3f()), distance);
		
		return new Collision(distance ,(distance.x < 0 && distance.y < 0 && distance.z < 0));
	}
	
	
	public void correctPosition(AABB box2, Collision data)
	{
		Vector3f correction = box2.center.sub(box2.center, center, new Vector3f());
		
		if(data.distance.x > data.distance.y && data.distance.x > data.distance.z) // if X greater then Y and Z
		{
			if(correction.x>0)
			{
				center.add(center, new Vector3f(data.distance.x,0,0), center);
			}else{
				center.add(center, new Vector3f(-data.distance.x,0,0), center);
			}
		}else{
			if(data.distance.y > data.distance.x && data.distance.y > data.distance.z) // if Y greater then X and Z
			{
				if(correction.y>0)
				{
					center.add(center, new Vector3f(0,data.distance.y,0), center);
				}else{
					center.add(center, new Vector3f(0, -data.distance.y,0), center);
				}
			}else{
				if(correction.z>0)
				{
					center.add(center, new Vector3f(0,0,data.distance.z), center);
				}else{
					center.add(center, new Vector3f(0,0, -data.distance.z), center);
				}
			}
		}
	}

	public Vector3f getCenter() {
		return center;
	}

	public Vector3f getHalfExtent() {
		return halfExtent;
	}

	public void setCenter(Vector3f center) {
		this.center = center;
	}
	
	
	
}
