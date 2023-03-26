package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PlayerBall extends Player{

    private BodyDef playerBodyDef;
    private Body playerBody;
    private boolean right = true;

    @Override
    public void play() {

    };

    PlayerBall(String name, World world) {
        this.name = name;
        playerBodyDef = new BodyDef();

        playerBodyDef.position.set(0f,43f);
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;

        playerBody = world.createBody(playerBodyDef);

        CircleShape playerShape = new CircleShape();
        playerShape.setRadius(10f);

        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.shape = playerShape;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = playerBody.createFixture(fixtureDef);
        playerShape.dispose();
    }

    public boolean isRight() {
        return right;
    }

    public Vector2 getPosition() {
        return playerBody.getPosition();
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void applyForce(float force) {
        playerBody.setAngularVelocity(force);
    }

}
