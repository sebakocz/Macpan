package com.bensep.macpan.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.myGameLib.Entity;
import com.bensep.macpan.myGameLib.GameWorld;
import com.bensep.macpan.tesxtures.Textures;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Pallet extends Entity {

    public Pallet(float x, float y, GameWorld gameWorld) {
        super(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE, 0, 0, (byte) 0, IN1, Textures.getInstance().pallet, 1, 1, gameWorld);
    }

    @Override
    public void onDeath() {
        //Todo: Update Score
        super.onDeath();
    }

    @Override
    public void updateMovement() {}

}
