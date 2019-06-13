package com.bensep.macpan.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.bensep.macpan.entities.Ghost;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.tesxtures.Textures;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Personality {
    private float xStartPos;
    private float yStartPos;
    private TextureRegion texture;
    private Vector2 targetTile;
    private PacManMaze maze;
    private Ghost ghost;
    private byte deadState;

    public Personality(PacManMaze maze) {
        targetTile = new Vector2(0f, 0f);
        texture = Textures.getInstance().clyde;
        xStartPos = 15.5f * TILE_SIZE;
        yStartPos = 18 * TILE_SIZE;
        this.maze = maze;
        deadState = 0;
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
                if (deadState >= 1) {
                    if (deadState == 1) {
                        targetTile = maze.getTileAt(13 * TILE_SIZE, 17 * TILE_SIZE).getPos();
                    } else {
                        targetTile = maze.getTileAt(14 * TILE_SIZE, 17 * TILE_SIZE).getPos();
                    }
                } else {
                    if (maze.getTileAt(ghost.getCenter().x, ghost.getCenter().y).y == 21 * TILE_SIZE) {
                        if (maze.getTileAt(ghost.getCenter().x, ghost.getCenter().y).x==13*TILE_SIZE) {
                            deadState=1;
                        } else if (maze.getTileAt(ghost.getCenter().x, ghost.getCenter().y).x == 14 * TILE_SIZE) {
                            deadState=2;
                        }
                    }
                    if (maze.getTileAt(ghost.getCenter().x, ghost.getCenter().y).x <= 13 * TILE_SIZE) {
                        targetTile = maze.getTileAt(14 * TILE_SIZE, 21 * TILE_SIZE).getPos();
                    } else {
                        targetTile = maze.getTileAt(13 * TILE_SIZE, 21 * TILE_SIZE).getPos();
                    }
                }
                break;
            case HOUSE:
                break;
        }
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }
}
