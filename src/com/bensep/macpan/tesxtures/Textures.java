package com.bensep.macpan.tesxtures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.myGameLib.ResourceHandler;

public class Textures {

    private static Textures instance;

    private Textures() {
        loadTextures();
    }

    public static Textures getInstance() {
        if (instance == null) {
            instance = new Textures();
        }
        return instance;
    }

    public TextureRegion pallet, energizer;
    public TextureRegion[] grid;
    public TextureRegion[] pacMan;


    public void loadTextures() {
        pallet = ResourceHandler.getInstance().getAtlas("PacManGrid").findRegion("pallet");
        energizer = ResourceHandler.getInstance().getAtlas("PacManGrid").findRegion("energizer");
        grid = new TextureRegion[13];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = ResourceHandler.getInstance().getAtlas("PacManGrid").findRegion("grid" + i);
        }

    }



}
