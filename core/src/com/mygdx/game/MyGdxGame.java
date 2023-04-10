package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.Vector;

public class MyGdxGame extends Game  {

	//Pixel level width and height of game
	private final int width = 320;
	private final int height = 180;

	public SpriteBatch batch;

	private final String name;//the title of the game

	private ArrayList<Player> players;// the players of the game

	public Vector<Vector2> level1OffsetsCols = new Vector(); // For offsetting the tiles manually that have been loaded from Tiled

	public InputMultiplexer inputMultiplexer;

	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;

	public ShapeRenderer shapeRenderer;

	private Color invisible = new Color(1,1,1,0);
	private boolean fullScreen = false;

	public BitmapFont font;
	public boolean run = false;
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		run = false;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new Level(this));
		shapeRenderer = new ShapeRenderer();
		inputMultiplexer = new InputMultiplexer();
	}

	public MyGdxGame(String name) {
		this.name = name;
		players = new ArrayList();
		level1OffsetsCols.add(new Vector2(width/2 - 80,16.0f));
		level1OffsetsCols.add(new Vector2(64,16.0f));
		level1OffsetsCols.add(new Vector2(27.7f,4.5f));
		inputMultiplexer = new InputMultiplexer();

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
		System.out.println("Player won");
		Gdx.app.exit();
	};

	public void declareLooser() {
		//Reset game for now
		System.out.println("Player lost");
		Gdx.app.exit();
	}

	public void createLevelBoundaries(World world) {
		BodyDef colBox = new BodyDef();
		colBox.type = BodyDef.BodyType.StaticBody;
		Body colBoxBody = world.createBody(colBox);
		colBoxBody.setTransform(0-16,0+height/2,0.0f);

		PolygonShape polyShape = new PolygonShape();
		polyShape.setAsBox(32/2,200/2);
		colBoxBody.createFixture(polyShape,0.0f);
		polyShape.dispose();

		Body colBoxBody2 = world.createBody(colBox);
		colBoxBody2.setTransform(width+17,0+height/2,0.0f);

		PolygonShape polyShape2 = new PolygonShape();
		polyShape2.setAsBox(32/2,200/2);
		colBoxBody2.createFixture(polyShape2,0.0f);
		polyShape2.dispose();

	}

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

	public void initFonts() {
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/RoentgenNbp-ojl0.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 14;
		parameter.color = Color.WHITE;

		font = generator.generateFont(parameter);

		font.usesIntegerPositions();
	}

	public void handleInput(GroupOfCards groupOfCards) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.R) && groupOfCards.allCardsSelected()) {
			run = true;
			parameter.color = invisible;
			font = generator.generateFont(parameter);

			//Disable card picking ui

			//Make visible the execution list order of the commands for the player ball

		}

		if(!fullScreen && Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
			makeFullscreen();
		}

		if(fullScreen && Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
			makeWindowed();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void makeFullscreen() {
		fullScreen = true;
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
	}

	public void makeWindowed() {
		fullScreen = false;
		Gdx.graphics.setWindowedMode(1280,720);
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

}
