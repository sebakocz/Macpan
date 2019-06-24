package com.bensep.macpan.handlers;

import com.badlogic.gdx.math.Vector2;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.tesxtures.Animations;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Pinky extends Personality {
    public Pinky(PacManMaze maze) {
        super(maze,13.5f * TILE_SIZE, 19*TILE_SIZE, Animations.getInstance().pinky,maze.getTileAt(3f * TILE_SIZE, 35f*TILE_SIZE).getPos());
    }

    @Override
    public void setChasePos() {

    }
}
