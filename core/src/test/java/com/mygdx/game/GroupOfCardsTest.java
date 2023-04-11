package com.mygdx.game;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.*;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author markm
 */
public class GroupOfCardsTest {
    
    public GroupOfCardsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown()   {}

    /**
     * Test of getCards method, of class GroupOfCards.
     */
    @Test
    public void testAddCardSuccess() {
        System.out.println("testing getCards() success");
        GroupOfCards instance = new GroupOfCards(5);

        Card purpleCard = new PurpleColourCard(new MyGdxGame("Test game"));

        instance.addCard(purpleCard);

        boolean contains = false;
        boolean expResult = true;
        if(instance.getCards().contains(purpleCard)) {
           contains = true;
        } else {
            contains = false;
        }
        assertEquals(expResult, contains);
    }

    /**
     * Test of getCard method, of class GroupOfCards.
     */
    @Test
    public void testGetCard() {
        System.out.println("getCard");
        int i = 0;
        GroupOfCards instance = null;
        Card expResult = null;
        Card result = instance.getCard(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCard method, of class GroupOfCards.
     */
    @Test
    public void testAddCard() {
        System.out.println("addCard");
        Card card = null;
        GroupOfCards instance = null;
        instance.addCard(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shuffle method, of class GroupOfCards.
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");
        GroupOfCards instance = null;
        instance.shuffle();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class GroupOfCards.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        GroupOfCards instance = null;
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSize method, of class GroupOfCards.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        int size = 0;
        GroupOfCards instance = null;
        instance.setSize(size);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of centerCards method, of class GroupOfCards.
     */
    @Test
    public void testCenterCards() {
        System.out.println("centerCards");
        GroupOfCards instance = null;
        instance.centerCards();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortCards method, of class GroupOfCards.
     */
    @Test
    public void testSortCards() {
        System.out.println("sortCards");
        MyGdxGame game = null;
        GroupOfCards instance = null;
        instance.sortCards(game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printCardNumbers method, of class GroupOfCards.
     */
    @Test
    public void testPrintCardNumbers() {
        System.out.println("printCardNumbers");
        GroupOfCards instance = null;
        instance.printCardNumbers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countDown method, of class GroupOfCards.
     */
    @Test
    public void testCountDown() {
        System.out.println("countDown");
        GroupOfCards instance = null;
        instance.countDown();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allCardsSelected method, of class GroupOfCards.
     */
    @Test
    public void testAllCardsSelected() {
        System.out.println("allCardsSelected");
        GroupOfCards instance = null;
        boolean expResult = false;
        boolean result = instance.allCardsSelected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderCollection method, of class GroupOfCards.
     */
    @Test
    public void testRenderCollection() {
        System.out.println("renderCollection");
        MyGdxGame game = null;
        GroupOfCards instance = null;
        instance.renderCollection(game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleCommands method, of class GroupOfCards.
     */
    @Test
    public void testHandleCommands() {
        System.out.println("handleCommands");
        PlayerBall player = null;
        GroupOfCards instance = null;
        instance.handleCommands(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renderCollectionBoundingBoxes method, of class GroupOfCards.
     */
    @Test
    public void testRenderCollectionBoundingBoxes() {
        System.out.println("renderCollectionBoundingBoxes");
        ShapeRenderer shapeRenderer = null;
        GroupOfCards instance = null;
        instance.renderCollectionBoundingBoxes(shapeRenderer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleInputs method, of class GroupOfCards.
     */
    @Test
    public void testHandleInputs() {
        System.out.println("handleInputs");
        Camera cam = null;
        MyGdxGame game = null;
        boolean simRunning = false;
        GroupOfCards instance = null;
        instance.handleInputs(cam, game, simRunning);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disposeCards method, of class GroupOfCards.
     */
    @Test
    public void testDisposeCards() {
        System.out.println("disposeCards");
        GroupOfCards instance = null;
        instance.disposeCards();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");

    
}
    
}
