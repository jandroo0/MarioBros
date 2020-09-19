package com.garcia.mariobros.Sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.garcia.mariobros.MarioBros;
import com.garcia.mariobros.Screens.GameScreen;

public class Mushroom extends Item {

    public Mushroom(GameScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("mushroom"), 0, 0, 16, 16);
        velocity = new Vector2(0.5f,  0.7f );
    }

    @Override
    public void defineItem() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / MarioBros.PPM);
        fdef.filter.categoryBits = MarioBros.ITEM_BIT;
        fdef.filter.maskBits =  MarioBros.MARIO_BIT |
                MarioBros.GROUND_BIT |
                MarioBros.OBJECT_BIT | MarioBros.BLANK_BIT |
                MarioBros.ENEMY_BIT | MarioBros.ENEMY_HEAD_BIT |
                MarioBros.ITEM_BIT | MarioBros.COIN_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void useItem(Mario mario) {
        destroy();
        mario.grow();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        velocity.y = body.getLinearVelocity().y;
        body.setLinearVelocity(velocity);
    }
}
