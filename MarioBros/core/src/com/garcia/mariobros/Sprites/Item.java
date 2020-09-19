package com.garcia.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.garcia.mariobros.MarioBros;
import com.garcia.mariobros.Screens.GameScreen;

public abstract class Item extends Sprite {

    protected GameScreen screen;
    protected World world;
    protected Vector2 velocity;
    protected boolean setToDestroy;
    protected boolean destroyed;
    protected Body body;

    public Item(GameScreen screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x, y);
        setBounds(getX(), getY(), 16 / MarioBros.PPM,  16 / MarioBros.PPM);
        defineItem();
        setToDestroy = false;
        destroyed = false;
    }

    public abstract void defineItem();
    public abstract void useItem(Mario mario);

    public void update(float deltaTime) {
        if(setToDestroy && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
        }
    }

    public void destroy() {
        setToDestroy = true;
    }

    @Override
    public void draw(Batch batch) {
        if(!destroyed)
            super.draw(batch);
    }

    public void reverseVel(boolean x, boolean y) {
        if(x) velocity.x = -velocity.x;
        if(y) velocity.y = -velocity.y;
    }
}
