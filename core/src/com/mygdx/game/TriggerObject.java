/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;


/**
 *
 * @author markm
 */
public class TriggerObject {


    private Body hazardBody;
    private Rectangle boundingRec;
    private PlayerBall player;


    float xPos, yPos;
    boolean isTouched = false;
    
      public TriggerObject(float xPos, float yPos, float width, float height, World world) {
        this.xPos = xPos;
        this.yPos = yPos;

        boundingRec = new Rectangle(xPos,yPos,width,height);
        BodyDef hazardColBox = new BodyDef();
        hazardColBox.type = BodyDef.BodyType.StaticBody;
        hazardBody = world.createBody(hazardColBox);

        hazardBody.setTransform(xPos,yPos,0.0f);
        PolygonShape polyShape = new PolygonShape();
        polyShape.setAsBox(boundingRec.getWidth()/2f, boundingRec.getHeight()/2f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polyShape;
        fixtureDef.isSensor = true;
        hazardBody.createFixture(fixtureDef);
        polyShape.dispose();
    }

    public Rectangle getBoundingRec() {
        return boundingRec;
    }

    public Body getTriggerBody() {
          return hazardBody;
    }

    public void setBoundingRec(Rectangle boundingRec) {
        this.boundingRec = boundingRec;
    }

}
