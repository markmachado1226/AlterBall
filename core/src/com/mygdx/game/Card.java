/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 */
public abstract class Card {
    //default modifier for child classes

    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */


    private int x, y;
    private float spriteX, spriteY;
    public float mouseX,mouseY;

    private Texture card;
    private Sprite cardSprite;
    public ShapeRenderer shapeRenderer;

    private float width, height;
    private Rectangle boundingRec;
    private Vector3 mousePos;

    public FreeTypeFontGenerator generator;
    public FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public Font someFont;

    @Override
    public abstract String toString();

    public abstract void renderCard(MyGdxGame game);
    public abstract void renderBoundingBox();
    public abstract void handleInput(Camera camera);
    public abstract void disableCard();
    public abstract void setProjectionMatrix(Matrix4 camCombined);

    //Getter and setters

    public void setX(int x) {
       this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public float getMouseX() {
        return mouseX;
    }

    public float getMouseY() {
        return mouseY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setCard(String path) {
        card = new Texture(Gdx.files.internal(path));
    }

    public Texture getCard() {
        return card;
    }

    public void setCardSprite(Texture t) {
        cardSprite = new Sprite(t);
    }

    public Sprite getCardSprite() {
        return cardSprite;
    }

    public void setWidth(float w) {
        width = w;
    }

    public void setHeight(float h) {
        height = h;
    }


    public void setBoundingRectangle(Rectangle r) {
        boundingRec = r;
    }

    public Rectangle getBoundingRectangle() {
        return boundingRec;
    }

    public void setMousePos(Vector3 mP) {
        mousePos = mP;
    }

    public Vector3 getMousePos() {
        return mousePos;
    }


}