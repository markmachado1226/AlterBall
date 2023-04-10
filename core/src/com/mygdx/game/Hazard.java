/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

/**
 *
 * @author markm
 */
public class Hazard {

    

  
    
    public int damage; 
    public Texture hazardTexture; 
    public Sprite hazardSprite; 
    public Vector2 Position; 
    public Rectangle boundingRec; 
    
    
      public Hazard(int damage, Texture hazardTexture, Sprite hazardSprite, Vector2 Position, Rectangle boundingRec) {
        this.damage = damage;
        this.hazardTexture = hazardTexture;
        this.hazardSprite = hazardSprite;
        this.Position = Position;
        this.boundingRec = boundingRec;
    }
      
      
      public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Texture getHazardTexture() {
        return hazardTexture;
    }

    public void setHazardTexture(Texture hazardTexture) {
        this.hazardTexture = hazardTexture;
    }

    public Sprite getHazardSprite() {
        return hazardSprite;
    }

    public void setHazardSprite(Sprite hazardSprite) {
        this.hazardSprite = hazardSprite;
    }

    public Vector2 getPosition() {
        return Position;
    }

    public void setPosition(Vector2 Position) {
        this.Position = Position;
    }

    public Rectangle getBoundingRec() {
        return boundingRec;
    }

    public void setBoundingRec(Rectangle boundingRec) {
        this.boundingRec = boundingRec;
    }
    
}
