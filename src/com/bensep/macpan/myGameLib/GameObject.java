package com.bensep.macpan.myGameLib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject extends Rectangle{

    protected TextureRegion texture;
    protected float xTextureOffset, yTextureOffset;
    protected byte collide;
    public static final byte IN1 = 1, IN2 = 2, IN3 = 4, IN4 = 8, OUT1 = 16, OUT2 = 32, OUT3 = 64, OUT4 =-128;

    public GameObject(float x, float y, float with, float height, float xTextureOffset, float yTextureOffset, byte collide, TextureRegion texture) {
        super(x, y, with, height);
        this.collide = collide;
        this.xTextureOffset = xTextureOffset;
        this.yTextureOffset = yTextureOffset;
        this.texture = texture;
    }

    public void update() {
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, x + xTextureOffset, y + yTextureOffset);
    }

    public byte getCollide() {
        return collide;
    }

    public void setCollide(byte collide) {
        this.collide = collide;
    }

    public boolean collide(GameObject gameObject) {
        boolean back = false;
        if ((gameObject.collide & (collide<<4)) > 0) {
            back = overlaps(gameObject);
        }
        return back;
    }
}
