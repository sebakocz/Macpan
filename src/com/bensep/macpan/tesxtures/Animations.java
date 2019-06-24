package com.bensep.macpan.tesxtures;

import com.badlogic.gdx.graphics.g2d.Animation;

import static com.bensep.macpan.constants.Constants.MOVEMENT_SPEED;

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
    public Animation energizer;
    public Animation clyde;
    public Animation blinky;
    public Animation pinky;
    public Animation inky;

    private void loadAnimations() {
        Textures textures = Textures.getInstance();
        pacMan = new Animation(2f*MOVEMENT_SPEED, textures.pacManWalk);
        energizer = new Animation(20f, textures.energizer, textures.empty);
        clyde = new Animation(6f, textures.clyde);
        blinky = new Animation(6f, textures.blinky);
        inky = new Animation(6f, textures.inky);
        pinky = new Animation(6f, textures.pinky);
    }



}
