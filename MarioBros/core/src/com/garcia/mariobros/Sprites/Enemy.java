package com.garcia.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.garcia.mariobros.Screens.GameScreen;

public abstract class Enemy extends Sprite {
    protected World world;
    protected GameScreen screen;

    public Body b2body;

    public Enemy(GameScreen screen, float x, float y) {
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x, y);
    }

    protected abstract void defineEnemy();
}
