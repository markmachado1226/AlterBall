package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Game;

import java.util.ArrayList;

public class MyGdxGame extends Game {

	public SpriteBatch batch;
	public Texture img;

	private final String name;//the title of the game
	private ArrayList<Player> players;// the players of the game

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
		this.setScreen(new Level1(this));
	}

	public MyGdxGame(String name) {
		this.name = name;
		players = new ArrayList();
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

	public void loadLevel(TiledMap tm, MapObjects objs, World world) {
		for(int x = 0; x < tm.getLayers().size(); x++) {
			//If current layer is not a tiled layer (layer for collision boxes)
			if(!(tm.getLayers().get(x) instanceof TiledMapTileLayer)) {
				//Parse the objects and create the collision boxes
				objs = tm.getLayers().get(x).getObjects();
				for(int y = 0; y < objs.getCount(); y++) {
					BodyDef colBox = new BodyDef();
					colBox.type = BodyDef.BodyType.StaticBody;
				}
			}
		}
	}


}
