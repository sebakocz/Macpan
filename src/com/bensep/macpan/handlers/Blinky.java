package com.bensep.macpan.handlers;

import com.badlogic.gdx.math.Vector2;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.tesxtures.Animations;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Blinky extends Personality {

    public Blinky(PacManMaze maze) {
        super(maze,13.5f * TILE_SIZE, 21*TILE_SIZE, Animations.getInstance().blinky,maze.getTileAt(26f * TILE_SIZE, 35f*TILE_SIZE).getPos());
    }
}
