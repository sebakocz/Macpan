package com.bensep.macpan.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.bensep.macpan.entities.Ghost;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.myGameLib.Direction;
import com.bensep.macpan.tesxtures.Textures;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Personality {
    private float xStartPos;
    private float yStartPos;
    private TextureRegion texture;
    private Vector2 targetTile;
    private PacManMaze maze;

    public Personality(PacManMaze maze) {
        targetTile = new Vector2(0f, 0f);
        texture = Textures.getInstance().clyde;
        xStartPos = 14 * TILE_SIZE;
        yStartPos = 21 * TILE_SIZE;
        this.maze = maze;
    }

    public float getXStartPos() {
        return xStartPos;
    }

    public float getYStartPos() {
        return yStartPos;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public Vector2 getTargetTile() {
        return targetTile;
    }

    public void update(Ghost.State state) {
        switch (state) {
            case CHASE:
                targetTile = maze.getTileAt(maze.getPacMan().getCenter().x, maze.getPacMan().getCenter().y).getPos();
                break;
            case SCATTER:
                break;
            case FRIGHTENED:
                break;
            case DEAD:
                targetTile = maze.getTileAt(14 * TILE_SIZE, 19 * TILE_SIZE).getPos();
                break;
            case House:
                break;
        }
    }
}
