package com.bensep.macpan.handlers;

import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.textures.Animations;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Clyde extends Personality {

    public Clyde(PacManMaze maze) {
        super(maze,15.5f * TILE_SIZE, 18*TILE_SIZE, Animations.getInstance().clyde,maze.getTileAt(0f * TILE_SIZE, 0f*TILE_SIZE).getPos());
    }

    @Override
    public void setChasePos() {
       if(distanceToPacman() > 8*TILE_SIZE){
           targetTile.set(maze.getTileAt(maze.getPacMan().getCenter().x, maze.getPacMan().getCenter().y).getPos());
       }
       else {
           targetTile.set(corner);
       }
    }

    private float distanceToPacman() {
        return ghost.getPos().dst(maze.getPacMan().getPos());
    }
}
