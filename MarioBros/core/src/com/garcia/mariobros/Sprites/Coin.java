package com.garcia.mariobros.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.garcia.mariobros.MarioBros;
import com.garcia.mariobros.Scenes.Hud;
import com.garcia.mariobros.Screens.GameScreen;

public class Coin extends InteractiveTileObject{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Coin(GameScreen screen, Rectangle bounds) {
        super(screen, bounds);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");

        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("COLLISION", "COIN");
        setCategoryFilter(MarioBros.BLANK_BIT);

        if(getCell().getTile() != tileSet.getTile(BLANK_COIN)) {
            Hud.addScore(300);
            MarioBros.manager.get("audio/sfx/coin.wav", Sound.class).play();
            getCell().setTile(tileSet.getTile(BLANK_COIN));
        } else
            MarioBros.manager.get("audio/sfx/bump.wav", Sound.class).play();
    }
}
