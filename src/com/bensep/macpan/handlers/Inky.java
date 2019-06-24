package com.bensep.macpan.handlers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.tesxtures.Animations;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Inky extends Personality {

    public Inky(PacManMaze maze) {
        super(maze,13.5f * TILE_SIZE, 21*TILE_SIZE, Animations.getInstance().inky,maze.getTileAt(26f * TILE_SIZE, 0f*TILE_SIZE).getPos());

    }

    @Override
    public void setChasePos() {

        switch (maze.getPacMan().getDirection()) {
            case UP:
                targetTile.set(0f * TILE_SIZE, 2f * TILE_SIZE);
                break;
            case LEFT:
                targetTile.set(-2 * TILE_SIZE, 0f * TILE_SIZE);
                break;
            case DOWN:
                targetTile.set(0f * TILE_SIZE, -2 * TILE_SIZE);
                break;
            case RIGHT:
                targetTile.set(2 * TILE_SIZE, 0f );
                break;
        }

        targetTile.add(maze.getTileAt(maze.getPacMan().getCenter()).getPos());

        targetTile.add(targetTile.sub(maze.getTileAt(maze.getGhost(0).getCenter()).getPos()));
    }
}
