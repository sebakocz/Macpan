package com.bensep.macpan.myGameLib;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity extends GameObject {

    protected double health;
    protected double maxHealth;
    protected byte dmgCollide;
    protected GameWorld gameWorld;

    public Entity(float x, float y, float with, float height, float xTextureOffset, float yTextureOffset, byte collide, byte dmgCollide, TextureRegion texture, double maxHealth, double health, GameWorld gameWorld) {
        super(x, y, with, height, xTextureOffset, yTextureOffset, collide, texture);
        this.gameWorld = gameWorld;
        this.health = health;
        this.maxHealth = maxHealth;
        this.dmgCollide = dmgCollide;
        gameWorld.addHoldingObject(x, y, this);
    }

    public void onDeath() {
        gameWorld.killEntity(this);
    }

    public abstract void updateMovement();

    @Override
    public void update() {
        if (health<=0) onDeath();
    }

    public boolean hit(Rectangle dmgArea,double amount, byte dmgCollide) {
        boolean out = false;
        if ((dmgCollide & (this.dmgCollide << 4)) > 0) {
            if (this.overlaps(dmgArea)) {
                out = true;
                health -= amount;
            }
        }
        return out;
    }

    public boolean translate(float x, float y) {
        gameWorld.removeHoldingObject(x, y, this);
        this.x += x;
        this.y += y;
        if (gameWorld.collide(this)) {
            this.x -= x;
            this.y -= y;
            gameWorld.addHoldingObject(x, y, this);
            return false;
        }
        gameWorld.addHoldingObject(x, y, this);
        return true;
    }

    public enum Directions {
        UP, DOWN, RIGHT, LEFT,
    }

}
