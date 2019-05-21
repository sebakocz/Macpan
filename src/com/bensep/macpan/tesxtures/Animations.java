package com.bensep.macpan.tesxtures;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animations {

    private static Animations instance;

    private Animations() {
        loadAnimations();
    }

    public static Animations getInstance() {
        if (instance == null) {
            instance = new Animations();
        }
        return instance;
    }

    public Animation pacMan;

    private void loadAnimations() {
        Textures textures = Textures.getInstance();
        pacMan = new Animation(4f, textures.pacMan);
    }



}
