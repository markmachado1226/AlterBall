package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    private TiledMap map = new TmxMapLoader().load("SampleMap.tmx");
    private MyGdxGame game;
    private PlayerBall player;

    private float unitScale = 1/32f;
    private OrthogonalTiledMapRenderer mapRenderer;

    private Viewport viewport;
    Level (MyGdxGame game) {

        this.game = game;
        debugRenderer = new Box2DDebugRenderer();
        mapRenderer = new OrthogonalTiledMapRenderer(map,1f);
        camera.setToOrtho(false,1280f,720f);
        camera.update();

        camera.position.x = 160;
        camera.position.y = 90;

        viewport = new FitViewport(320,180,camera);

        player = new PlayerBall("Ball",world);

        game.initFonts();

        //Load level collisions
        game.loadLevel(map,world);
        game.createLevelBoundaries(world);

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

        game.font.draw(game.batch, "Press 'r' to run.",10,game.getHeight()-10);

        if(game.run == true)
            world.step(1/60f,6,2);

        if(player.getPosition().x > 300) {
            player.setRight(false);
        }

        if(player.getPosition().x < 25) {
            player.setRight(true);
        }


        if(player.isRight() == true) {
            player.applyForce(-20.0f);
        } else {
            player.applyForce(20.0f);
        }




        mapRenderer.setView(camera);
        mapRenderer.render();

        game.handleInput();

        game.batch.end();

        debugRenderer.render(world, camera.combined);


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
