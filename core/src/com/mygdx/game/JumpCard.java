package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class JumpCard extends Card implements InputProcessor {

    private boolean selected = false;

    JumpCard(MyGdxGame game) {
        setCard("Colour.png");
        setCardSprite(getCard());

        setMousePos(new Vector3());

        setX(0);
        setY(0);

        setBoundingRectangle(new Rectangle(getX(),getY(),getCard().getWidth(),getCard().getHeight()));

        getCardSprite().setOrigin(0,0);
        getCardSprite().setPosition(getX(),getY());
        setFont("fonts/RoentgenNbp-ojl0.ttf");

        setCardFlipSound("Sounds/cardFlip.mp3");

        setGame(game);

        System.out.println(getCardNumber());

    }

    JumpCard(int x, int y, MyGdxGame game) {
        setCard("Colour.png");
        setCardSprite(getCard());

        setMousePos(new Vector3());

        setX(x);
        setY(y);

        setBoundingRectangle(new Rectangle(getX(),getY(),getCard().getWidth(),getCard().getHeight()));

        getCardSprite().setOrigin(0,0);
        getCardSprite().setPosition(getX(),getY());
        setFont("fonts/RoentgenNbp-ojl0.ttf");

        setCardFlipSound("Sounds/cardFlip.mp3");

        setGame(game);

    }


    @Override
    public void playCommand() {
        
    }
}