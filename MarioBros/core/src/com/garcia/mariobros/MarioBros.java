package com.garcia.mariobros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garcia.mariobros.Screens.GameScreen;

public class MarioBros extends Game { // from windows
	public static final int V_WIDTH = 400;
	public static final int v_HEIGHT = 208;
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this));
	}


	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
