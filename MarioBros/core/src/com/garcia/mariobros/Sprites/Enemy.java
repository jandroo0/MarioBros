package com.garcia.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.garcia.mariobros.Screens.GameScreen;

public abstract class Enemy extends Sprite {
    protected World world;
    protected GameScreen screen;

    public Body b2body;
    public Vector2 velocity;

    public Enemy(GameScreen screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x, y);
        defineEnemy();

        velocity =  new Vector2(.8f, 0);
        b2body.setActive(false);
    }

    public void reverseVel(boolean x, boolean y) {
        if(x) velocity.x = -velocity.x;
        if(y) velocity.y = -velocity.y;
    }

    protected abstract void defineEnemy();
    public abstract void hitOnHead(Mario mario);
    public abstract void update(float deltaTime);
}
