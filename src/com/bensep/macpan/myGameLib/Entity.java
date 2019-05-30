package com.bensep.macpan.myGameLib;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends GameObject {

    protected double health;
    protected double maxHealth;
    protected byte dmgCollide;
    protected GameWorld gameWorld;
    private Vector2 lastPos;

    public Entity(float x, float y, float with, float height, float xTextureOffset, float yTextureOffset, byte collide, byte dmgCollide, TextureRegion texture, double maxHealth, double health, GameWorld gameWorld) {
        super(x, y, with, height, xTextureOffset, yTextureOffset, collide, texture);
        this.gameWorld = gameWorld;
        this.health = health;
        this.maxHealth = maxHealth;
        this.dmgCollide = dmgCollide;
        lastPos = new Vector2();
        gameWorld.addHoldingObject(this);
    }

    public void onDeath() {
        gameWorld.killEntity(this);
    }

    public abstract void updateMovement();

    @Override
    public void update() {
        if (health<=0) onDeath();
    }

    public boolean hit(Rectangle dmgArea,float amount, byte dmgCollide) {
        boolean out = false;
        if ((dmgCollide & (this.dmgCollide << 4)) > 0) {
            if (this.overlaps(dmgArea)) {
                out = true;
                health -= amount;
            }
        }
        return out;
    }

    public boolean translate(float moveX, float moveY) {
        gameWorld.removeHoldingObject(this);
        lastPos.x = x;
        lastPos.y = y;
        x += moveX;
        y += moveY;
        if (!gameWorld.contains(getCenter())) {
            if (center.x < 0) x += gameWorld.width;
            if (center.y < 0) y += gameWorld.height;
            if (center.x >= gameWorld.width) x -= gameWorld.width;
            if (center.y >= gameWorld.height) y -= gameWorld.height;
        }
        if (gameWorld.collide(this)) {
            x = lastPos.x;
            y = lastPos.y;
            gameWorld.addHoldingObject(this);
            return false;
        }
        gameWorld.addHoldingObject(this);
        return true;
    }

}
