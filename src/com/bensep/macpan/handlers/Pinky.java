package com.bensep.macpan.handlers;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.textures.Animations;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Pinky extends Personality {
    public Pinky(PacManMaze maze) {
        super(maze,13.5f * TILE_SIZE, 18*TILE_SIZE, Animations.getInstance().pinky,maze.getTileAt(3f * TILE_SIZE, 35f*TILE_SIZE).getPos());
    }

    @Override
    public void setChasePos() {
        switch (maze.getPacMan().getDirection()) {
            case UP:
                targetTile.set(-4f * TILE_SIZE, 4f * TILE_SIZE);
                break;
            case LEFT:
                targetTile.set(-4f * TILE_SIZE, 0);
                break;
            case DOWN:
                targetTile.set(0, -4f * TILE_SIZE);
                break;
            case RIGHT:
                targetTile.set(4f * TILE_SIZE, 0);
                break;
        }
        targetTile.add(maze.getTileAt(maze.getPacMan().getCenter()).getPos());

    }
}
