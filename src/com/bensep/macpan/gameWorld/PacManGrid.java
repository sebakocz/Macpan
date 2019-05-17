package com.bensep.macpan.gameWorld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bensep.macpan.myGameLib.GameObject;
import com.bensep.macpan.myGameLib.GameWorld;

import static com.bensep.macpan.constants.Constants.*;

public class PacManGrid extends GameWorld {

    public PacManGrid(OrthographicCamera gameCamera) {
        super(gameCamera, WORLD_WIDTH, WORLD_HIGHT, TILE_SIZE);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HIGHT; y++) {
                worldGrid[x][y].render(spriteBatch);
            }
        }
    }
}
