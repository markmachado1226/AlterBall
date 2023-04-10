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
import com.badlogic.gdx.math.Vector2;
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
    private boolean commandFinished = false;

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

    public void setCard(String path) {
       card = new Texture(Gdx.files.internal(path));
    }

    public Texture getCard() {
        return card;
    }

    public void setCardSprite(Texture card) {
        cardSprite = new Sprite(card);
    }

    public Sprite getCardSprite() {
        return cardSprite;
    }

    public void setMousePos(Vector3 vec) {
       mousePos = vec;
    }

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

    public void setSeconds(int s) {
        seconds = s;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean b) {
        selected = b;
    }

    public void setBoundingRectangle(Rectangle br) {
       boundingRec = br;
    }

    public void moveSpriteX(float x) {
       spriteX += x;
       cardSprite.setX(spriteX);
    }


    public void setCardFlipSound(String path) {
       cardFlip = Gdx.audio.newSound(Gdx.files.internal(path));
    }

    public void setGame(MyGdxGame game) {
       this.game = game;
    }

    public int getCardNumber() {
        return cardNumber;
    }


    public void moveSpriteY(float y) {
        spriteY += y;
        cardSprite.setY(spriteY);
    }


    public void setFont(String fontPath) {
        generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
    }

    public void setPos(int x,int y) {
        this.x = x;
        this.y = y;

        boundingRec = new Rectangle(this.x,this.y,card.getWidth(),card.getHeight());
        cardSprite.setPosition(this.x,this.y);
    }


    public void setCommandFinished(boolean b) {
       commandFinished = b;
    }

    public boolean getCommandFinished() {
        return commandFinished;
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
        if(selected == false) {
            if (boundingRec.contains(mousePos.x, mousePos.y)) {
                if(i3 == Input.Buttons.LEFT) {
                    cardNumber = cardNumberCounter;
                    cardNumberCounter++;
                    seconds = scrolledAmount;
                    moveSpriteY(-30);
                    fontY = game.getHeight() - 140f;
                    selected = true;
                }

            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        if(selected == true) {
            if (boundingRec.contains(mousePos.x, mousePos.y)) {
                if(i3 == Input.Buttons.RIGHT) {
                    cardNumberCounter--;
                    moveSpriteY(0);
                    fontY = game.getHeight() - 110f;
                    selected = false;
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
        if(selected == false) {
            if(boundingRec.contains(mousePos.x,mousePos.y)) {
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

    public void handleCommand(PlayerBall player) {
        if(seconds <= 0) {
            playCommand(player);
        }
    }

    public void renderCard(MyGdxGame game) {
        cardSprite.draw(game.batch);
        if(selected == true) {
            font.draw(game.batch, Integer.toString(seconds), this.x + parameter.size, game.getHeight() - 140);
        }

    }

    public void renderBoundingBox(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(this.x,this.y, boundingRec.width, boundingRec.height);
    }


    public void handleInput(Camera camera,MyGdxGame game, boolean simRunning) {
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();

        camera.unproject(mousePos.set(mouseX,mouseY,0));

        if(!simRunning) {
            if(selected == false) {

                if (boundingRec.contains(mousePos.x, mousePos.y)) {
                    fontX = this.x + parameter.size;
                    fontY = game.getHeight() - 110f;
                    if (cardFlipSoundPlayed == false) {
                        cardFlip.play(1.0f, 1.5f, 0.0f);
                        cardFlipSoundPlayed = true;
                    }
                    //Move card up
                    if (spriteY < maxRaise) {
                        moveSpriteY(Gdx.graphics.getDeltaTime() * spriteAnimationSpeed);
                    }

                    font.draw(game.batch, Integer.toString(scrolledAmount), fontX, fontY);

                } else {
                    //Move card back down
                    if (spriteY > 0) {
                        moveSpriteY(Gdx.graphics.getDeltaTime() * -spriteAnimationSpeed);
                    }
                    cardFlipSoundPlayed = false;
                }
            } else {
                font.draw(game.batch, Integer.toString(scrolledAmount), fontX, fontY);
            }
        }
    }

    public void dispose() {
        generator.dispose();
        font.dispose();
        card.dispose();
    }


    public abstract void playCommand(PlayerBall player);

    public void disableCard() {

    }

}