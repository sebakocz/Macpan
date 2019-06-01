package com.bensep.macpan.entities;

import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.myGameLib.Entity;
import com.bensep.macpan.tesxtures.Textures;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Pallet extends Entity {

    public Pallet(float x, float y, PacManMaze gameWorld) {
        super(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE, 0, 0, (byte) 0, IN1, Textures.getInstance().pallet, 1, 1, gameWorld);
    }

    @Override
    public void onDeath() {
        //Todo: Update Score
        ((PacManMaze) gameWorld).getPacMan().stop(1f);
        super.onDeath();
    }

    @Override
    public void updateMovement() {}

}
