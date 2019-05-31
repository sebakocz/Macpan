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
    public TextureRegion[][] maze;
    public TextureRegion[] pacManWalk;
    public TextureRegion empty;
    public TextureRegion barrier;


    public void loadTextures() {
        pallet = ResourceHandler.getInstance().getAtlas("Maze").findRegion("Pallet");
        energizer = ResourceHandler.getInstance().getAtlas("Maze").findRegion("Energizer");
        maze = new TextureRegion[8][8];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < ((i == 2) ? 8 : 4); j++) {
                maze[i][j] = ResourceHandler.getInstance().getAtlas("Maze").findRegion("Wall_" + i, j);
            }
        }
        empty = ResourceHandler.getInstance().getAtlas("Maze").findRegion("Wall_7",3);
        barrier = ResourceHandler.getInstance().getAtlas("Maze").findRegion("Wall_7",2);
        pacManWalk = new TextureRegion[4];
        for (int i = 0; i < pacManWalk.length; i++) {
            pacManWalk[i] = ResourceHandler.getInstance().getAtlas("Entities").findRegion("PacMan_Walk", i);
        }

    }



}
