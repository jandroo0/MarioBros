package com.garcia.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garcia.mariobros.MarioBros;
import com.garcia.mariobros.Scenes.Hud;

public class GameScreen implements Screen {

    private MarioBros game;
    private OrthographicCamera camera;
    private Viewport gamePort;

    private Hud hud;

    public GameScreen(MarioBros game) {
        this.game = game;
        camera = new OrthographicCamera();
        gamePort = new StretchViewport(MarioBros.V_WIDTH, MarioBros.v_HEIGHT, camera);

        hud = new Hud(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f,0.5f,0.7f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
