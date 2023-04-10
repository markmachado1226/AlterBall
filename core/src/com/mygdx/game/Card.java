/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 */
public abstract class Card implements InputProcessor{
    //default modifier for child classes

    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */

    public float spriteAnimationSpeed = 120;
    //Maximum height the card will be raised when the mouse hovers over it
    public float maxRaise = 15;


    private static int cardNumberCounter;
    private int cardNumber;
    private int x, y;
    private float fontX, fontY;
    private boolean cardFlipSoundPlayed = false;
    private Sound cardFlip;
    private boolean selected;

    private float spriteX, spriteY;
    public float mouseX,mouseY;

    private Texture card;
    private Sprite cardSprite;

    private float width, height;
    private Rectangle boundingRec;
    private Vector3 mousePos;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont font;

    private int seconds = 0;

    private MyGdxGame game;


    private int scrolledAmount = 0;


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

    public void moveSpriteX(float x) {
       spriteX += x;
       cardSprite.setX(x);
    }

    public float getSpriteX() {
        return spriteX;
    }

    public void moveSpriteY(float y) {
        spriteY += y;
        cardSprite.setY(spriteY);
    }

    public float getSpriteY() {
        return spriteY;
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

   public void setCardFlipSound(String soundPath) {
        cardFlip = Gdx.audio.newSound(Gdx.files.internal(soundPath));
   }

    public Sound getCardFlipSound() {
        return cardFlip;
    }

    public void setWidth(float w) {
        width = w;
    }

    public void setHeight(float h) {
        height = h;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int s) {
        seconds = s;
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

    public boolean getCardFlipSoundPlayed() {
        return cardFlipSoundPlayed;
    }

    public void setCardFlipSoundPlayed(boolean p) {
        cardFlipSoundPlayed = p;
    }

    public void setFont(String fontPath) {
        generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFontSize(int s) {
        parameter.size = s;
    }

    public int getFontSize() {
        return parameter.size;
    }

    public void setSelected(boolean b)  {
        selected = b;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setGame(MyGdxGame game) {
        this.game = game;
    }

    public MyGdxGame getGame() {
        return game;
    }

    public void setFontX(float x) {
       fontX = x;
    }

    public float getFontX() {
        return fontX;
    }

    public void setFontY(float y) {
        fontY = y;
    }
    public float getFontY() {
        return fontY;
    }

    public int getCardNumberCounter() {
        return cardNumberCounter;
    }

    public void incrementCardNumberCounter() {
        cardNumberCounter++;
    }

    public void decrementCardNumberCounter() {
        cardNumberCounter--;
    }

    public void setCardNumber() {
        cardNumber = cardNumberCounter;
    }


    public int getCardNumber() {
        return cardNumber;
    }

    public int getScrolledAmount() {
        return scrolledAmount;
    }

    public void setScrolledAmount(int s) {
        scrolledAmount = s;
    }


    public void setPos(int x,int y) {
        setX(x);
        setY(y);

        setBoundingRectangle(new Rectangle(getX(),getY(),getCard().getWidth(),getCard().getHeight()));
        getCardSprite().setPosition(getX(),getY());
    }


    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        if(getSelected() == false) {
            if (getBoundingRectangle().contains(getMousePos().x, getMousePos().y)) {
                if(i3 == Input.Buttons.LEFT) {
                    setCardNumber();
                    incrementCardNumberCounter();
                    setSeconds(scrolledAmount);
                    moveSpriteY(-30);
                    setFontY(getGame().getHeight() - 140f);
                    setSelected(true);
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        if(getSelected() == true) {
            if (getBoundingRectangle().contains(getMousePos().x, getMousePos().y)) {
                if(i3 == Input.Buttons.RIGHT) {
                    decrementCardNumberCounter();
                    moveSpriteY(0);
                    setFontY(getGame().getHeight() - 110f);
                    setSelected(false);
                }

            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        if(getSelected() == false) {
            if(getBoundingRectangle().contains(getMousePos().x,getMousePos().y)) {
                if(v1 < 0) {
                    scrolledAmount++;
                    //Clamp scrolled amount value
                    if(scrolledAmount > 20) {
                        scrolledAmount = 20;
                    }
                } else {
                    if(v1 > 0) {
                        scrolledAmount--;
                        //Clamp scrolled amount value
                        if(scrolledAmount < 0) {
                            scrolledAmount = 0;
                        }
                    }
                }
            }
        }

        return false;
    }

    public String toString() {
        return null;
    }

    public void renderCard(MyGdxGame game) {
        getCardSprite().draw(game.batch);
        if(selected == true) {
            getFont().draw(game.batch, Integer.toString(getSeconds()), getX() + getFontSize(), game.getHeight() - 140);
        }
    }

    public void renderBoundingBox(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(getX(),getY(), getBoundingRectangle().width, getBoundingRectangle().height);
    }


    public void handleInput(Camera camera,MyGdxGame game, boolean simRunning) {
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();

        camera.unproject(getMousePos().set(mouseX,mouseY,0));

        if(!simRunning) {
            if(getSelected() == false) {

                if (getBoundingRectangle().contains(getMousePos().x, getMousePos().y)) {
                    setFontX(getX() + getFontSize());
                    setFontY(getGame().getHeight() - 110f);
                    if (getCardFlipSoundPlayed() == false) {
                        getCardFlipSound().play(1.0f, 1.5f, 0.0f);
                        setCardFlipSoundPlayed(true);
                    }
                    //Move card up
                    if (getSpriteY() < maxRaise) {
                        moveSpriteY(Gdx.graphics.getDeltaTime() * spriteAnimationSpeed);
                    }

                    getFont().draw(getGame().batch, Integer.toString(getScrolledAmount()), getFontX(), getFontY());

                } else {
                    //Move card back down
                    if (getSpriteY() > 0) {
                        moveSpriteY(Gdx.graphics.getDeltaTime() * -spriteAnimationSpeed);
                    }
                    setCardFlipSoundPlayed(false);
                }
            } else {
                getFont().draw(getGame().batch, Integer.toString(getScrolledAmount()), getFontX(), getFontY());
            }
        }

    }

    public abstract void playCommand();

    public void disableCard() {

    }

}