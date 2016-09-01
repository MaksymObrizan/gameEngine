package entities;

import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class GameObject extends Object{
	private Vector3f position;
	private TexturedModel renderComponent;
	

		
	public TexturedModel getRenderComponent() {
		return renderComponent;
	}

	public void setRenderComponent(TexturedModel renderComponent) {
		this.renderComponent = renderComponent;
	}

	public GameObject(Vector3f position, TexturedModel renderComponent) {
		super();
		this.position = position;
		this.renderComponent = renderComponent;
	}

	public GameObject(Vector3f coordinates) {
		super();
		this.position = coordinates;
	}

	public void update()
	{
	
	}
	
	
}
