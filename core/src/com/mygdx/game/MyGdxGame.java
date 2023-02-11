package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

}
