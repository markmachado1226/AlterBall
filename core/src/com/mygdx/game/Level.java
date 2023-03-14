package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Level implements Screen {



    private OrthographicCamera camera = new OrthographicCamera();
    private Box2DDebugRenderer debugRenderer;
    private World world = new World(new Vector2(0,-90),true);

    TiledMap map = new TmxMapLoader().load("SampleMap.tmx");

    MyGdxGame game;

    BodyDef floorBodyDef;
    BodyDef playerBodyDef;
    float unitScale = 1/32f;
    OrthogonalTiledMapRenderer mapRenderer;

    Viewport viewport;

    Level (MyGdxGame game) {

        this.game = game;
        debugRenderer = new Box2DDebugRenderer();
        mapRenderer = new OrthogonalTiledMapRenderer(map,1f);
        camera.setToOrtho(false,1280f,720f);
        camera.update();

        System.out.println(camera.position.y);

        camera.position.x = 160;
        camera.position.y = 90;

        viewport = new FitViewport(320,180,camera);

        playerBodyDef = new BodyDef();

        playerBodyDef.position.set(0f,40f);
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;

        Body playerBody = world.createBody(playerBodyDef);

        CircleShape playerShape = new CircleShape();
        playerShape.setRadius(10f);

        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.shape = playerShape;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = playerBody.createFixture(fixtureDef);
        playerShape.dispose();

        //Load level collisions
        game.loadLevel(map,world);

    }

    @Override
    public void show() {

    }



    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0f,1f);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        world.step(1/60f,6,2);

        mapRenderer.setView(camera);
        mapRenderer.render();

        debugRenderer.render(world, camera.combined);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
    viewport.update(width,height);
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
