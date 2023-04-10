package com.mygdx.game;

/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size;//the size of the grouping
    private float timePassed = 0.0f;

    private int cardExecuted = 0;

    public GroupOfCards(int size) {
        this.size = size; cards = new ArrayList<>(size);
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(int i) {
       return cards.get(i);
    }

    public void addCard(Card card) {
       cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }

    public void centerCards() {
        int centerCard = 0;
        if(cards.size() % 2 == 0) {
            centerCard = cards.size()/2;
        } else {
            centerCard --;
            centerCard = cards.size()/2;
        }

       for(Card c : cards) {
           if(cards.size() % 2 == 0) {
               c.setPos(320/2 + cards.indexOf(c)*32 - (centerCard*32),0);
           } else {
               c.setPos(320/2 + cards.indexOf(c)*32 - (centerCard*32)-16,0);
           }

       }
    }

    public void sortCards(MyGdxGame game) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                return Integer.compare(card1.getCardNumber(),card2.getCardNumber());
            }
        });
    }

    public void printCardNumbers() {
        for(Card c : cards) {
            System.out.println("Card "+ cards.indexOf(c) + " with number of " + c.getCardNumber());
        }
    }


    //Run timers on cards
    public void countDown() {
    timePassed += Gdx.graphics.getDeltaTime();
    for(int x = cardExecuted; x < cards.size(); x++) {
        if(timePassed > 1) {
            cards.get(x).setSeconds(cards.get(x).getSeconds()- 1);
            timePassed = 0;
        }

        if(cards.get(x).getSeconds() <= 0) {
            if(cardExecuted < cards.size())
                cardExecuted++;
        }
    }
    }

    //Checks if all cards are selected
    public boolean allCardsSelected() {
        for(Card c : cards) {
            if(c.getSelected() == false) {
                return false;
            }
        }
        return true;
    }

    public void renderCollection(MyGdxGame game) {
        long currentTime = TimeUtils.millis();
        for(Card c : cards) {
            c.renderCard(game);
        }
    }

    public void handleCommands(PlayerBall player) {
       for(Card c : cards) {
           if(c.getSeconds() <= 0) {
               c.playCommand(player);
           }
       }
    }

    public void renderCollectionBoundingBoxes(ShapeRenderer shapeRenderer) {
        for(Card c : cards) {
            c.renderBoundingBox(shapeRenderer);
        }
    }

    public void handleInputs(Camera cam,MyGdxGame game,boolean simRunning) {
        for(Card c : cards) {
            c.handleInput(cam,game,simRunning);
        }
    }

    public void disposeCards() {
        for(Card c : cards) {
            c.dispose();
        }
    }

}//end class
