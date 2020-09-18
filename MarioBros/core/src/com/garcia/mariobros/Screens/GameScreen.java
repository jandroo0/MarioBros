package com.garcia.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garcia.mariobros.MarioBros;
import com.garcia.mariobros.Scenes.Hud;
import com.garcia.mariobros.Sprites.Mario;
import com.garcia.mariobros.Tools.B2WorldCreator;
import com.garcia.mariobros.Tools.WorldContactListener;

import javax.xml.bind.ValidationException;

public class GameScreen implements Screen {

    private MarioBros game;
    private TextureAtlas atlas;

    private OrthographicCamera camera;
    private Viewport gamePort;

    private Hud hud;

    // tiled map
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // box2d vars
    private World world;
    private Box2DDebugRenderer b2dr;

    // mario
    private Mario player;

    // music
    private Music music;

    public GameScreen(MarioBros game) {
        this.game = game;

        atlas = new TextureAtlas("atlas/atlas.pack");

        camera = new OrthographicCamera();
        gamePort = new StretchViewport(MarioBros.V_WIDTH / MarioBros.PPM, MarioBros.v_HEIGHT / MarioBros.PPM, camera);

        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tilemap/tilemap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1 / MarioBros.PPM);

        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0); // center camera

        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(this);

        player = new Mario(this);

        world.setContactListener(new WorldContactListener());

        music = MarioBros.manager.get("audio/music/mario_music.ogg", Music.class);
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    // player input
    public void handleInput(float deltaTime) {
        // up
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(new Vector2(0, 4), player.b2body.getWorldCenter(), true);
        // right
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(player.getVel(), 0), player.b2body.getWorldCenter(), true);
        // left
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-player.getVel(), 0), player.b2body.getWorldCenter(), true);
    }

    public void update(float deltaTime) {
        // player input
        handleInput(deltaTime);

        // takes 1 step in the physics simulation (60 times per second)
        world.step(1/60f, 6, 2);

        player.update(deltaTime);
        hud.update(deltaTime);

        // camera follows player x
        camera.position.x = player.b2body.getPosition().x;

        camera.update(); // update camera
        renderer.setView(camera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        update(deltaTime);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render(); // render game map

        game.batch.setProjectionMatrix(camera.combined); // set batch to what camera sees

        // draw mario
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); // set batch to what camera sees
        hud.stage.draw(); // draw hud
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
