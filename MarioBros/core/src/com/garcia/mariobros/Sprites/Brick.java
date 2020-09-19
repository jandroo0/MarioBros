package com.garcia.mariobros.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.garcia.mariobros.MarioBros;
import com.garcia.mariobros.Scenes.Hud;
import com.garcia.mariobros.Screens.GameScreen;

public class Brick extends InteractiveTileObject{
    private final int HIT_BRICK = 3;

    public Brick(GameScreen screen, MapObject object) {
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        Gdx.app.log("COLLISION", "BRICK");

        if(getCell().getTile() != tileSet.getTile(HIT_BRICK)) {
            Hud.addScore(300);
            MarioBros.manager.get("audio/sfx/break.wav", Sound.class).play();
            getCell().setTile(tileSet.getTile(HIT_BRICK));
        }
    }

}
