package com.bensep.macpan.myGameLib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public abstract class WorldTile extends GameObject {

    protected ArrayList<GameObject> holdingObjects;

    public WorldTile(float x, float y, float with, float height, float xTextureOffset, float yTextureOffset, byte collide, TextureRegion texture) {
        super(x, y, with, height, xTextureOffset, yTextureOffset, collide, texture);
        holdingObjects = new ArrayList<>();
    }

    public void addEntityAbove(GameObject gameObject) {
        holdingObjects.add(gameObject);
    }

    public abstract void update();



    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, x + xTextureOffset, y + yTextureOffset);
        for (GameObject e:holdingObjects
        ) {
            e.render(spriteBatch);
        }
    }

    @Override
    public boolean collide(GameObject gameObject) {
        boolean back = super.collide(gameObject);
        if (!back) {
            for (GameObject o:holdingObjects
            ) {
                if (o.collide(gameObject)) {
                    back = true;
                    break;
                }
            }
        }
        return back;
    }

}
