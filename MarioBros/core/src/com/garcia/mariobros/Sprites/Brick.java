package com.garcia.mariobros.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.garcia.mariobros.MarioBros;
import com.garcia.mariobros.Scenes.Hud;
import com.garcia.mariobros.Screens.GameScreen;

public class Brick extends InteractiveTileObject{

    public Brick(GameScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("COLLISION", "BRICK");
        setCategoryFilter(MarioBros.DESTROYED_BIT);

        Hud.addScore(200);
        MarioBros.manager.get("audio/sfx/break.wav", Sound.class).play();
        getCell().setTile(null);
    }

}
