package com.garcia.mariobros.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.garcia.mariobros.Sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if ("head".equals(fixA.getUserData()) || "head".equals(fixB.getUserData())) { // is collision with mario head
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB; // decide which is head
            Fixture object = head == fixA ? fixB : fixA; // other fixture is the object

            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) { // is object an extension of tileObject (coin or brick)
                ((InteractiveTileObject) object.getUserData()).onHeadHit(); // calls method on head hit for object
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
