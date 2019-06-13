package com.bensep.macpan.gameWorld;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.entities.Ghost;
import com.bensep.macpan.myGameLib.Direction;

public class SetDirectionPart extends RestrictedWorldPart {

    private Direction direction;

    public SetDirectionPart(float x, float y, TextureRegion texture, byte restricted, Direction direction) {
        super(x, y, texture, restricted);
        this.direction = direction;
    }

    @Override
    public void update() {
        if (direction != Direction.NONE) {
            holdingObjects.forEach(object ->{
                try {
                    ((Ghost)object).overwriteDirection(direction);
                }catch (Exception ignored){}
            });
        }
    }
}
