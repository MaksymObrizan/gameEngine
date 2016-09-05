package managers;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import collision.AABB;
import collision.Collision;
import entities.Entity;
import entities.GameObject;

public class ColliderManager {

	
	/**
	 * @param objectList
	 */
	public void calculateCollision(List<Entity> objectList)
	{
		for(int i = 0; i<objectList.size();i++)
		{
				Entity objectA = objectList.get(i);
				if(objectA.isMovable()){
					Entity objectB = null;
					for(int j = 0; j<objectList.size();j++)
					{
						if(i!=j)
						{
								if(objectB == null)
								{ 
										objectB = objectList.get(j);
								}
								
								Vector3f lenght1 = getLength(objectB.getCollider().getCenter(), objectA.getPosition());
								Vector3f lenght2 = getLength(objectList.get(j).getCollider().getCenter(), objectA.getPosition());
								
								if(lenght1.lengthSquared() > lenght2.lengthSquared())
								{
									objectB = objectList.get(j);
								}
								
						}
					} // end of for(j)
					if(getLength(objectB.getCollider().getCenter(), objectA.getPosition()).lengthSquared() < 250){
						AABB box = null;
						if(objectB.innerColider != null)
						{
							for(int k=0; k < objectB.innerColider.size(); k++)
							{
								if(box == null) box = objectB.innerColider.get(k);
								Vector3f lenght1 = getLength(box.getCenter(), objectA.getPosition());
								Vector3f lenght2 = getLength(objectB.innerColider.get(k).getCenter(), objectA.getPosition());
								
								if(lenght1.lengthSquared() > lenght2.lengthSquared())
								{
									box = objectB.innerColider.get(k);
								}
							}
						}else
						{
							box= objectB.getCollider();
						}
							
							Collision data  = objectA.getCollider().getCollision(box);
							
							if(data.isIntersecting)
							{
								objectA.getCollider().correctPosition(box, data);
								if(objectB.isMovable())
									objectB.getCollider().correctPosition(objectA.getCollider(), data);
							}
					}
				}//end if(isSolid)
		}//end of for(i)
				
	}
	
	private Vector3f getLength(Vector3f start, Vector3f end)
	{
		Vector3f lenght = start.sub(start, end,new Vector3f());
		return lenght;
	}
}
