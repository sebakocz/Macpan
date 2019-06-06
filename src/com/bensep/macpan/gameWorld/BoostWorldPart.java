package com.bensep.macpan.gameWorld;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.entities.Ghost;
import com.bensep.macpan.myGameLib.GameObject;

public class BoostWorldPart extends WorldPart {

    private PacManMaze gameWorld;
    public BoostWorldPart(float x, float y, byte collide, TextureRegion texture, PacManMaze gameWorld) {
        super(x, y, collide, texture);
        this.gameWorld = gameWorld;
    }

    @Override
    public void update() {
        for (GameObject gameObject:holdingObjects
             ) {
            try {
                ((Ghost) gameObject).setSpeed(gameWorld.getGhostTunnelSpeed(), 1f);
            } catch (Exception ignored) {}
        }
        super.update();
    }
}
