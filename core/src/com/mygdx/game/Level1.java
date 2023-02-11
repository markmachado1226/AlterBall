package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.concurrent.Future;

public class Level1 implements Screen {

    private OrthographicCamera camera = new OrthographicCamera();
    private Box2DDebugRenderer debugRenderer;

    private World world = new World(new Vector2(0,-10),true);
    private BodyDef floorBodyDef = new BodyDef();
    private BodyDef playerBodyDef = new BodyDef();

    MyGdxGame game;
    Level1 (MyGdxGame game) {

        this.game = game;

        camera.setToOrtho(false,1280f,720f);

        debugRenderer = new Box2DDebugRenderer();


        floorBodyDef.type = BodyDef.BodyType.StaticBody;
        Body floorBody = world.createBody(floorBodyDef);

        playerBodyDef.position.set(500f,100f);
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        Body playerBody = world.createBody(playerBodyDef);

        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(camera.viewportWidth,10.0f);
        floorBody.createFixture(floorShape,0.0f);

        CircleShape playerShape = new CircleShape();
        playerShape.setRadius(20f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = playerShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = playerBody.createFixture(fixtureDef);

        floorShape.dispose();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0.0f,1f);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        world.step(1/60f,6,2);
        debugRenderer.render(world, camera.combined);

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
