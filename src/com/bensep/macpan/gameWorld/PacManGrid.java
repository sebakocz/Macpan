package com.bensep.macpan.gameWorld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.myGameLib.GameObject;
import com.bensep.macpan.myGameLib.GameWorld;
import com.bensep.macpan.tesxtures.Textures;

import static com.bensep.macpan.constants.Constants.*;

public class PacManGrid extends GameWorld {

    public PacManGrid() {
        super(WORLD_WIDTH, WORLD_HEIGHT, TILE_SIZE);
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                setGridAt(x,y,false, Textures.getInstance().grid[5]);
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                worldGrid[x][y].render(spriteBatch);
            }
        }
    }

    public void setGridAt(int x, int y, boolean collide, TextureRegion textureRegion) {
        if (collide)
            worldGrid[x][y] = new WorlPart(x, y, GameObject.IN1, textureRegion);
        else
            worldGrid[x][y] = new WorlPart(x, y, (byte) 0, textureRegion);
    }
}
