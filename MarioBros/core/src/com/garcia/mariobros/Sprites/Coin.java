package com.garcia.mariobros.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.garcia.mariobros.MarioBros;
import com.garcia.mariobros.Scenes.Hud;
import com.garcia.mariobros.Screens.GameScreen;

public class Coin extends InteractiveTileObject{
    private final int BLANK_COIN = 28;

    public Coin(GameScreen screen, MapObject object) {
        super(screen, object);

        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        Gdx.app.log("COLLISION", "COIN");
        setCategoryFilter(MarioBros.BLANK_BIT);

        if(getCell().getTile() != tileSet.getTile(BLANK_COIN)) {
            if(object.getProperties().containsKey("mushroom")) {
                screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x , body.getPosition().y + 24 / MarioBros.PPM), Mushroom.class));
                MarioBros.manager.get("audio/sfx/powerup_spawn.wav", Sound.class).play();
            }
            if(object.getProperties().containsKey("coin")) {
                Hud.addScore(300);
                MarioBros.manager.get("audio/sfx/coin.wav", Sound.class).play();
            }
            getCell().setTile(tileSet.getTile(BLANK_COIN));

        } else
            MarioBros.manager.get("audio/sfx/bump.wav", Sound.class).play();
    }
}
