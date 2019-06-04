package com.bensep.macpan.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bensep.macpan.handlers.Personality;
import com.bensep.macpan.myGameLib.Direction;
import com.bensep.macpan.myGameLib.Entity;
import com.bensep.macpan.myGameLib.GameWorld;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Ghost extends Entity {

    private Personality personality;
    private Direction direction;
    private State state;

    public Ghost(Personality personality, GameWorld gameWorld) {
        super(personality.getxStartPos(), personality.getyStartPos(), TILE_SIZE, TILE_SIZE, -4, -4, (byte) (OUT1 | OUT2), OUT2, null, 1, 1, gameWorld);
        this.personality = personality;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void updateMovement() {

    }

    public enum State {
        CHASE,
        SCATTER,
        FRIGHTENED,
        House
    }
}
