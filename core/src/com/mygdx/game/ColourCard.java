package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class ColourCard extends Card{


    @Override
    public String toString() {
        return null;
    }

    @Override
    public void renderCard(MyGdxGame game) {
        getCardSprite().draw(game.batch);
    }

    @Override
    public void renderBoundingBox() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(getX(),getY(), getBoundingRectangle().width, getBoundingRectangle().height);
        shapeRenderer.end();
    }

    @Override
    public void handleInput(Camera camera) {
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();
        camera.unproject(getMousePos().set(mouseX,mouseY,0));

        if(getBoundingRectangle().contains(getMousePos().x,getMousePos().y)) {
            System.out.println("Mouse over");
            //Move card up
        } else {
            System.out.println("Mouse out");
            //Move card back down
        }
    }

    @Override
    public void disableCard() {

    }

    @Override
    public void setProjectionMatrix(Matrix4 camCombined) {
        shapeRenderer.setProjectionMatrix(camCombined);
    }

    ColourCard() {
        setCard("Colour.png");
        shapeRenderer = new ShapeRenderer();
        setCardSprite(getCard());
        setMousePos(new Vector3());

        setX(0);
        setY(0);

        setBoundingRectangle(new Rectangle(getX(),getY(),getCard().getWidth(),getCard().getHeight()));

        getCardSprite().setOrigin(0,0);
        getCardSprite().setPosition(getX(),getY());
    }

    ColourCard(int x, int y) {
        setCard("Colour.png");
        setCardSprite(getCard());
        shapeRenderer = new ShapeRenderer();

        setMousePos(new Vector3());

        setX(x);
        setY(y);

        setBoundingRectangle(new Rectangle(getX(),getY(),getCard().getWidth(),getCard().getHeight()));

        getCardSprite().setOrigin(0,0);
        getCardSprite().setPosition(getX(),getY());
    }

}