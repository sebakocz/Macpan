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
    public TextureRegion[][] grid;
    public TextureRegion[] pacMan;
    public TextureRegion empty;
    public TextureRegion barrier;


    public void loadTextures() {
        pallet = ResourceHandler.getInstance().getAtlas("PacManGrid").findRegion("pallet");
        energizer = ResourceHandler.getInstance().getAtlas("PacManGrid").findRegion("energizer");
        grid = new TextureRegion[7][4];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = ResourceHandler.getInstance().getAtlas("PacManGrid").findRegion("grid" + i + j);
            }

        }
        empty = ResourceHandler.getInstance().getAtlas("PacManGrid").findRegion("grid70");
        barrier = ResourceHandler.getInstance().getAtlas("PacManGrid").findRegion("grid71");
        pacMan = new TextureRegion[4];
        for (int i = 0; i < pacMan.length; i++) {
            pacMan[i] = ResourceHandler.getInstance().getAtlas("PacManMove").findRegion("PacMan" + i);
        }

    }



}
