package com.bensep.macpan.gameWorld;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RestrictedWorldPart extends WorldPart {
    public RestrictedWorldPart(float x, float y, byte collide, TextureRegion texture, byte restricted) {
        super(x, y, collide, texture);
        this.restricted = restricted;
    }

    private byte restricted;

    public byte isRestricted() {
        return restricted;
    }

    public void setRestricted(byte restricted) {
        this.restricted = restricted;
    }

    @Override
    public boolean checkCollide(byte collide) {
        if (restricted != 0 && (restricted & collide) == restricted) {
            return true;
        }
        return super.checkCollide(collide);
    }
}
