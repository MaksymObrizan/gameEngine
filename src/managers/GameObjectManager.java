package managers;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.GameObject;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import objects.CubeUnit;
import objects.House;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrain.Terrain;
import terrain.TerrainTexture;
import terrain.TerrainTexturePack;
import textures.ModelTexture;

public class GameObjectManager {

	Player player;
	static Terrain terrain;
	List<Entity> objects = new ArrayList<Entity>();
	
	MasterRenderer renderer;
	Loader loader;
	ColliderManager colliderMaster = new ColliderManager();
	
	//*********
	ModelTexture player_texture;
	RawModel model1;
	TexturedModel staticModel1;
	 
	ModelTexture house_texture;
	RawModel house_model;
	TexturedModel house_textModel;
	//*********
	
	public GameObjectManager() {
//		createPlayer();
	}

	public void update()
	{
		work();
		collision();
		render();
	}
	
	public void cleanUp()
	{
		renderer.cleanUp();
		loader.cleanUp();
	}
	
	
	
	
	
	
	public Player getPlayer() {
		return player;
	}

	public MasterRenderer getRenderer() {
		return renderer;
	}
	public GameObjectManager setRenderer(MasterRenderer renderer) {
		this.renderer = renderer;
		return this;
	}
	
	
	public Loader getLoader() {
		return loader;
	}
	public GameObjectManager setLoader(Loader loader) {
		this.loader = loader;
		return this;
	}

	public void work()
	{
		player.move(terrain);
		for(GameObject object:objects)
		{
			object.update();
			((Entity)object).work(terrain);
		}
	}
	
	public void collision()
	{
		colliderMaster.calculateCollision(objects);
	}
	
	public void render()
	{
		for(Entity object: objects)
		{
			renderer.processEntity(object);
		}
		renderer.processEntity(player);
		
		renderer.processTerrain(terrain);
	}
	
	public void createPlayer()
	{
		player_texture = new ModelTexture(loader.loadTexture("models/panzer"));
		player_texture.setShineDamper(100);
		player_texture.setReflectivity(0);
		model1 = OBJLoader.loadObjModel("models/panzer", loader);
		staticModel1 = new TexturedModel(model1, player_texture);
		 
		
		player = new Player(staticModel1, new Vector3f(-50,0,-50), 0, 0,0, 1);
		player.setSolid(false);
		objects.add(player);
		System.out.println("createPlayer");
	}
	
	public void createTerrain()
	{
		//--------------------Textures------------
				TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("map/background"));
				TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("map/red"));
				TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("map/green"));
				TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("map/blue"));
				
				TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
				TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("map/blendMap"));
				//---------------------endTextures---------
				

				terrain = new Terrain(0,0,loader, texturePack, blendMap,"map/heightMap");
	}
	
	public void addCubeUnit()
	{
		objects.add(new CubeUnit(staticModel1, new Vector3f(1,1,1), 0, 0,0, 1));
		System.out.println("addCube");
	}
	
	public void addHouse()
	{
		house_texture = new ModelTexture(loader.loadTexture("models/house"));
		house_texture.setShineDamper(100);
		house_texture.setReflectivity(0);
		house_model = OBJLoader.loadObjModel("models/house", loader);
		house_textModel = new TexturedModel(house_model, house_texture);
		 		
		objects.add(new House(house_textModel, new Vector3f(1,1,1), 0,0,0, 1,terrain));
		System.out.println("created House");
	}
	
	public int getListCount()
	{
		return objects.size();
	}
	
}
