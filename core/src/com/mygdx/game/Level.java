package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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

import java.util.ArrayList;


public class Level implements Screen, ContactListener{

    private OrthographicCamera camera = new OrthographicCamera();
    private Box2DDebugRenderer debugRenderer;
    private World world = new World(new Vector2(0,-90),true);

    private TiledMap map = new TmxMapLoader().load("SampleMap.tmx");
    private MyGdxGame game;
    private PlayerBall player;

    private float unitScale = 1/32f;
    private OrthogonalTiledMapRenderer mapRenderer;

    private Viewport viewport;

    private Card purpleColourCard;
    private Card jumpCard;

    private GroupOfCards groupOfCards;

    private TriggerObject purpleHazardColBox;

    private ArrayList<TriggerObject> finishTriggerObjects;

    private TriggerObject finishTriggerObject;


    Level (MyGdxGame game) {

        this.game = game;
        debugRenderer = new Box2DDebugRenderer();
        mapRenderer = new OrthogonalTiledMapRenderer(map,1f);
        camera.setToOrtho(false,1280f,720f);
        camera.update();

        finishTriggerObjects = new ArrayList<>();

        camera.position.x = 160;
        camera.position.y = 90;

        viewport = new FitViewport(320,180,camera);

        player = new PlayerBall("Ball",world,"playerSphere.png");
        game.addPlayer(player);

        game.initFonts();

        purpleColourCard = new PurpleColourCard(game);
        purpleHazardColBox = new TriggerObject(112,48,32,32,world);

        finishTriggerObject = new TriggerObject(304,50,32,32,world);

        purpleHazardColBox.setTarget(player);
        jumpCard = new JumpCard(game);

        groupOfCards = new GroupOfCards(5);
        groupOfCards.addCard(purpleColourCard);
        groupOfCards.addCard(jumpCard);
        groupOfCards.shuffle();
        groupOfCards.centerCards();

        finishTriggerObjects.add(finishTriggerObject);


        for(Card c : groupOfCards.getCards()) {
            game.inputMultiplexer.addProcessor(c);
        }



        //Load level collisions
        game.loadLevel(map,world);
        game.createLevelBoundaries(world);

        world.setContactListener(this);

        Gdx.input.setInputProcessor(game.inputMultiplexer);

    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0,0,0f,1f);

        game.shapeRenderer.setProjectionMatrix(camera.combined);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.font.draw(game.batch, "Press 'r' to run.",10,game.getHeight()-10);


        if(game.run == true) {
            world.step(1/60f,6,2);
            groupOfCards.sortCards(game);
            groupOfCards.countDown();
            groupOfCards.handleCommands(player);
        }


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

        game.handleInput(groupOfCards);

        groupOfCards.renderCollection(game);
        groupOfCards.handleInputs(camera,game, game.run);


        player.update();
        player.renderPlayerSprite(game);

        game.batch.end();

        /*
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(Color.RED);
        groupOfCards.renderCollectionBoundingBoxes(game.shapeRenderer);
        game.shapeRenderer.end();
         */

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
        groupOfCards.disposeCards();
        game.dispose();
        player.dispose();
    }

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getBody() == player.getPlayerBody() || contact.getFixtureB().getBody() == player.getPlayerBody()) {
                if(!player.getHasCollided()) {
                    player.bounceSound.play();
                    player.setHasCollided(true);
                }
        }

       if(contact.getFixtureA().getBody() == player.getPlayerBody() && contact.getFixtureB().getBody() == purpleHazardColBox.getTriggerBody() || contact.getFixtureB().getBody() == player.getPlayerBody() && contact.getFixtureA().getBody() == purpleHazardColBox.getTriggerBody())  {
            if(!player.getIsPlayerPurple()) {
                game.declareLooser();
            }

       }

        for(TriggerObject t : finishTriggerObjects) {
            if(contact.getFixtureA().getBody() == player.getPlayerBody() && contact.getFixtureB().getBody() == t.getTriggerBody() || contact.getFixtureB().getBody() == player.getPlayerBody() && contact.getFixtureA().getBody() == t.getTriggerBody())  {
                t.isTouched = true;
            }
        }
        //Check if all finishTriggerObjects have been collided with by the player
        int count = 0;
        for(TriggerObject t : finishTriggerObjects) {
            if(t.isTouched == false) {
                count++;
            }
        }
        if(!(count > 0)) {
           game.declareWinner();
        }
    }

    @Override
    public void endContact(Contact contact) {
        if(contact.getFixtureA().getBody() == player.getPlayerBody() || contact.getFixtureB().getBody() == player.getPlayerBody()) {
            if(player.getPlayerBody().getLinearVelocity().y > 2) {
                player.setHasCollided(false);
            }

        }
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
