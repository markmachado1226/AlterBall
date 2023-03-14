package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Game;


import java.util.ArrayList;
import java.util.Vector;

public class MyGdxGame extends Game {

	//Pixel level width and height of game
	public final int width = 320;
	public final int height = 180;

	//Tile size
	public final int tileSize = 32;


	public SpriteBatch batch;
	public Texture img;

	private final String name;//the title of the game
	private ArrayList<Player> players;// the players of the game

	public Vector<Vector2> level1OffsetsCols = new Vector();

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new Level(this));
	}

	public MyGdxGame(String name) {
		this.name = name;
		players = new ArrayList();
		level1OffsetsCols.add(new Vector2(width/2 - 80,16.0f));
		level1OffsetsCols.add(new Vector2(64,16.0f));
		level1OffsetsCols.add(new Vector2(27.7f,4.5f));
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the players of this game
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players of this game
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	/**
	 * Play the game. This might be one method or many method calls depending on your game.
	 */
	public void play() {

	};

	/**
	 * When the game is over, use this method to declare and display a winning player.
	 */
	public void declareWinner() {

	};

	public void loadLevel(TiledMap tm, World world) {
			//If current layer is not a tiled layer (layer for collision boxes)
				//Parse the objects and create the collision boxes
				MapObjects col = tm.getLayers().get("Collision").getObjects();
				MapObjects colRotated = tm.getLayers().get("CollisionRotated").getObjects();

				for(int y = 0; y < col.getCount(); y++) {
					MapObject obj = col.get(y);
					createBoxCollisions(obj,world,y,level1OffsetsCols);
				}

			for(int y = 0; y < colRotated.getCount(); y++) {
				MapObject obj = colRotated.get(y);
				createBoxCollisions(obj,world,level1OffsetsCols.size()-1 + y,level1OffsetsCols);
			}

	}

	public void createBoxCollisions(MapObject obj,World world, int offsetIndex,Vector<Vector2> offsetVector) {
		if(obj instanceof RectangleMapObject) {
			RectangleMapObject rectangleMapObject = (RectangleMapObject) obj;
			Rectangle rectangle = rectangleMapObject.getRectangle();

			float xPos = rectangle.getX() + offsetVector.get(offsetIndex).x;
			float yPos = rectangle.getY() + offsetVector.get(offsetIndex).y;

			float angle = 0.0f;
			angle = rectangleMapObject.getProperties().get("rotation",0.0f,Float.class);

			BodyDef colBox = new BodyDef();
			colBox.type = BodyDef.BodyType.StaticBody;
			Body colBoxBody = world.createBody(colBox);

			colBoxBody.setTransform(xPos,yPos,angle*8.5f);
			PolygonShape polyShape = new PolygonShape();
			polyShape.setAsBox(rectangle.getWidth()/2f, rectangle.getHeight()/2f);
			colBoxBody.createFixture(polyShape,0.0f);
			polyShape.dispose();

		}
	}
}
