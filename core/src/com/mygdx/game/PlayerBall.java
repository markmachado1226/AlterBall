package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PlayerBall extends Player {

    private BodyDef playerBodyDef;
    private Body playerBody;
    private Fixture fixture;
    private boolean hasCollided = false;
    private Sprite playerSprite;

    private boolean right = true;

    Sound bounceSound;

    @Override
    public void play() {

    };

    PlayerBall(String name, World world,String spritePath) {
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

        fixture = playerBody.createFixture(fixtureDef);
        playerShape.dispose();

        bounceSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bounce.mp3"));

        playerSprite = new Sprite(new Texture(Gdx.files.internal(spritePath)));
        playerSprite.setSize(20,20);

    }

    public void updateSpritePos() {
        playerSprite.setOrigin(0+20/2,0+20/2);
        playerSprite.setPosition(playerBody.getPosition().x - 20/2,playerBody.getPosition().y - 20/2);
        playerSprite.setRotation(-playerBody.getAngle() * MathUtils.radDeg);
    }

    public void renderPlayerSprite(MyGdxGame game) {
       playerSprite.draw(game.batch);
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

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getBody() == playerBody || contact.getFixtureB().getBody() == playerBody) {
            if(!hasCollided) {
                bounceSound.play();
                hasCollided = true;
            }
        }



    }

    @Override
    public void endContact(Contact contact) {
        if(contact.getFixtureA().getBody() == playerBody || contact.getFixtureB().getBody() == playerBody) {
            if(playerBody.getLinearVelocity().y > 2) {
                hasCollided = false;
            }

        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
