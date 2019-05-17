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

    public Animation pacManLeft;
    public Animation pacManRight;
    public Animation pacManUp;
    public Animation pacManDown;

    private void loadAnimations() {
        Textures textures = Textures.getInstance();
        TextureRegion[] frames = new TextureRegion[2];
        frames[0] = textures.pacMan[1];
        frames[1] = textures.pacMan[2];
        pacManUp = new Animation(1 / 10f, textures.pacMan[1], textures.pacMan[2]);
        pacManDown = new Animation(1 / 10f, textures.pacMan[4], textures.pacMan[5]);
        pacManLeft = new Animation(1 / 10f, textures.pacMan[7], textures.pacMan[8]);
        pacManRight = new Animation(1 / 10f, textures.pacMan[10], textures.pacMan[11]);
    }



}
