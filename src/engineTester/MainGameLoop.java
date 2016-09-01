package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import managers.GameObjectManager;
import models.RawModel;
import models.TexturedModel;
import objects.CubeUnit;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrain.Terrain;
import terrain.TerrainTexture;
import terrain.TerrainTexturePack;
import textures.ModelTexture;
import toolbox.Maths;

public class MainGameLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DisplayManager.createDisplay();
		GameObjectManager gameObjectManager = new GameObjectManager().setRenderer(new MasterRenderer()).setLoader(new Loader());
		
//		ModelTexture player_texture = new ModelTexture(gameObjectManager.getLoader().loadTexture("models/cube_unit_blue"));
//
//		player_texture.setShineDamper(100);
//		player_texture.setReflectivity(0);
		
//		RawModel model1 = OBJLoader.loadObjModel("models/cube_unit", gameObjectManager.getLoader());
//		TexturedModel staticModel1 = new TexturedModel(model1, player_texture);
		 
		
		ModelTexture fernAtlas = new ModelTexture(gameObjectManager.getLoader().loadTexture("leafAtlas"));
		fernAtlas.setNumberOfRows(2);
		fernAtlas.setReflectivity(0);
		fernAtlas.setShineDamper(25);
		
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("leaf", gameObjectManager.getLoader()), fernAtlas);
		
		
		
		List<Entity> entities = new ArrayList<Entity>();
		
		
		List<GuiTexture> guis = new ArrayList<>();
		GuiTexture gui = new GuiTexture(gameObjectManager.getLoader().loadTexture("gui"), new Vector2f(-0.9f,-0.9f), new Vector2f(0.1f,0.1f));
		guis.add(gui);
		
		GuiRenderer guiRenderer = new GuiRenderer(gameObjectManager.getLoader());
		
		Random rand = new Random(67000);
		
		
//		for(int i=0; i<500; i++)
//		{
//			float x = rand.nextFloat() * Terrain.SIZE/4 *-1;
//			float z = rand.nextFloat() * Terrain.SIZE/4 *-1;
//			float y = terrain.getHeightOfTerrain(x,z);
//			int j = i/100;
//			//entities.add(new Entity(fern, j, new Vector3f(x,y,z),0,0,0,1));
//		}
		
//		CubeUnit cube = ;
//		entities.add(cube);
//		Player player = new Player(staticModel1, new Vector3f(-50,0,-50), 0, 0,0, 1);
		
		Light light = new Light(new Vector3f(-50,1000,-50), new Vector3f(1,1,1));

//		List<Entity> entities = new ArrayList<Entity>();
//		entities.add(entity1);
		
		
		
		gameObjectManager.createTerrain();
		gameObjectManager.createPlayer();
	
		for(int i=0; i<100;i++)
		gameObjectManager.addCubeUnit();
		

		Camera camera = new Camera(gameObjectManager.getPlayer());
		
		System.out.println("ObjectListCount = " + gameObjectManager.getListCount());
		while(!Display.isCloseRequested())
		{
			camera.move();
			gameObjectManager.update();
//			
			gameObjectManager.getRenderer().render(light, camera);
			guiRenderer.render(guis);
		

			DisplayManager.updateDisplay();
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
				break;
			
//			if(player.collider.getCollision(cube.collider))
//			{
//				System.out.print("Intersect!!");
//			}
		}
		
		guiRenderer.cleanUp();
		gameObjectManager.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
