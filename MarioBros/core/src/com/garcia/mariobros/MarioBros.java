package com.garcia.mariobros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garcia.mariobros.Screens.GameScreen;

public class MarioBros extends Game { // from windows
	public static final int V_WIDTH = 400;
	public static final int v_HEIGHT = 208;
	public static final float PPM = 100;

	public static final short GROUND_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short BLANK_BIT = 32;
	public static final short OBJECT_BIT = 64;
	public static final short ENEMY_BIT = 128;

	public SpriteBatch batch;

	public static AssetManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();

		manager = new AssetManager();
		manager.load("audio/music/mario_music.ogg", Music.class);
		manager.load("audio/sfx/coin.wav", Sound.class);
		manager.load("audio/sfx/bump.wav", Sound.class);
		manager.load("audio/sfx/break.wav", Sound.class);
		manager.finishLoading();

		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
		batch.dispose();
	}
}
