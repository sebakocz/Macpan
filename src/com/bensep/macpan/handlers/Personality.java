package com.bensep.macpan.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.bensep.macpan.entities.Ghost;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.tesxtures.Animations;
import com.bensep.macpan.tesxtures.Textures;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public abstract class Personality {
    private float xStartPos;
    private float yStartPos;
    private Animation animation;
    private Vector2 targetTile;
    private Vector2 corner;
    protected PacManMaze maze;
    private Ghost ghost;
    private byte deadState;

    public Personality(PacManMaze maze, float x, float y , Animation animation, Vector2 corner) {
        this.corner = corner;
        targetTile = corner;
        this.animation = animation;
        xStartPos = x;
        yStartPos = y;
        this.maze = maze;
        deadState = 0;
    }

    public float getXStartPos() {
        return xStartPos;
    }

    public float getYStartPos() {
        return yStartPos;
    }

    public Animation getAnimation() {
        return animation;
    }

    public TextureRegion getKeyFrame() {
        return animation.getKeyFrame(Gdx.graphics.getFrameId(),true);
    }

    public Vector2 getTargetTile() {
        return targetTile;
    }

    public abstract Vector2 getChasePos();

    public void update(Ghost.State state) {
        switch (state) {
            case CHASE:
                targetTile = getChasePos();
                break;
            case SCATTER:
                targetTile = corner;
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
        }
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }
}
