package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Screens.MainGameScreen;
import com.mygdx.game.Screens.MenuScreen;


/**
 *  Class name: DefenseGame.java
 *  Yilin Li
 *  Description: This class will be used for initial the window's size of the game. When player start the application
 *  System will get into menu screen.
 */
public class DefenseGames extends Game {
	public SpriteBatch batch;
	public static final int windowsWidth = 1366;
	public static final int windowsHeight = 768;

	// This method will let the application getting into Menu Screen whenever player start the game.
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
