package com.bensep.macpan.handlers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.bensep.macpan.myGameLib.Direction;
import com.bensep.macpan.tesxtures.Animations;
import com.bensep.macpan.tesxtures.Textures;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Personality {
    private float xStartPos;
    private float yStartPos;
    private TextureRegion[] textures;
    private Vector2 targetTile;

    public Personality() {
        targetTile = new Vector2(0f, 0f);
        textures = Textures.getInstance().clyde;
        xStartPos = 14 * TILE_SIZE;
        yStartPos = 21 * TILE_SIZE;
    }

    public float getXStartPos() {
        return xStartPos;
    }

    public float getYStartPos() {
        return yStartPos;
    }

    public TextureRegion getTexture(Direction direction) {
        switch (direction) {
            case UP:
                return textures[0];
            case LEFT:
                return textures[1];
            case DOWN:
                return textures[2];
            case RIGHT:
                return textures[3];
        }
        return null;
    }

    public Vector2 getTargetTile() {
        return targetTile;
    }
}
