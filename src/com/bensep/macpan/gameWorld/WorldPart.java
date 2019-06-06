package com.bensep.macpan.gameWorld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.myGameLib.WorldTile;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class WorldPart extends WorldTile {


    public WorldPart(float x, float y, byte collide, TextureRegion texture) {
        super(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE, 0, 0, collide, texture);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, x + xTextureOffset, y + yTextureOffset);
    }
}
