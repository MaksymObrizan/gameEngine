package toolbox;

import java.util.Random;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;

public class Maths {
	static Random rand = new Random(67243);
	static long seed = 61829450;
	
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale)
	{
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		
		Matrix4f.rotate((float)Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
		
		
		Matrix4f.scale(new Vector3f(scale,scale,scale), matrix, matrix);
		return matrix;
	}
	
	public static float barryCentric(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos) {
		float det = (p2.z - p3.z) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.z - p3.z);
		float l1 = ((p2.z - p3.z) * (pos.x - p3.x) + (p3.x - p2.x) * (pos.y - p3.z)) / det;
		float l2 = ((p3.z - p1.z) * (pos.x - p3.x) + (p1.x - p3.x) * (pos.y - p3.z)) / det;
		float l3 = 1.0f - l1 - l2;
		return l1 * p1.y + l2 * p2.y + l3 * p3.y;
	}
	
	public static Matrix4f createViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		
		Matrix4f.rotate((float)Math.toRadians(camera.getPitch()), new Vector3f(1,0,0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float)Math.toRadians(camera.getYaw()), new Vector3f(0,1,0), viewMatrix, viewMatrix);
		
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		
		return viewMatrix;
	}
	public static float distanceVector2f(Vector3f posThis, Vector2f posTarget)
	{
		Vector2f dist = new Vector2f(posThis.getX()-posTarget.getX(), posThis.getZ()-posTarget.getY());
		float distance = (float)Math.sqrt(dist.getX()*dist.getX() + dist.getY()*dist.getY());
		return distance;
	}
	
	public static Vector2f normalize(Vector2f input)
	{
		float sum = (float) (1/Math.sqrt(input.getX()*input.getX() + input.getY()*input.getY()));
		return new Vector2f(input.getX()*sum,input.getY()/sum);
	}
	
	public static float getAngle(Vector2f A, Vector2f B)
	{
		return (float) Math.acos((A.x*B.x+A.y*B.y)/( Math.sqrt(A.x*A.x+A.y*A.y)*Math.sqrt(B.x*B.x+B.y*B.y) ));
	}
	
	public static float getNextFloat(){
		return rand.nextFloat();

	}
	
	public static float getPseudoGaussianRand()
	{
		int iter = 3;
		float sum = 0;
		for(int i =0; i<iter; i++)
		{
			sum += rand.nextFloat();
		}
		sum /= iter;
		return sum*2;
	}
}
