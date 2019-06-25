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

    public TextureRegion dot, energizer;
    public TextureRegion[][] maze;
    public TextureRegion[] pacManWalk;
    public TextureRegion empty;
    public TextureRegion barrier;
    public TextureRegion frightened;
    public TextureRegion[] clyde;
    public TextureRegion[] blinky;
    public TextureRegion[] pinky;
    public TextureRegion[] inky;
    public TextureRegion[] eyes;
    public TextureRegion[] numbers;


    public void loadTextures() {
        dot = ResourceHandler.getInstance().getAtlas("Maze").findRegion("Dot");
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
        frightened = ResourceHandler.getInstance().getAtlas("Entities").findRegion("Fright");
        clyde = new TextureRegion[2];
        blinky = new TextureRegion[2];
        pinky = new TextureRegion[2];
        inky = new TextureRegion[2];
        for (int i = 0; i < 2; i++) {
            clyde[i] = ResourceHandler.getInstance().getAtlas("Entities").findRegion("Clyde", i);
            blinky[i] = ResourceHandler.getInstance().getAtlas("Entities").findRegion("Blinky", i);
            pinky[i] = ResourceHandler.getInstance().getAtlas("Entities").findRegion("Pinky", i);
            inky[i] = ResourceHandler.getInstance().getAtlas("Entities").findRegion("Inky", i);
        }
        eyes = new TextureRegion[4];
        eyes[0] = ResourceHandler.getInstance().getAtlas("Entities").findRegion("Ghost_Eyes_Up");
        eyes[1] = ResourceHandler.getInstance().getAtlas("Entities").findRegion("Ghost_Eyes_Left");
        eyes[2] = ResourceHandler.getInstance().getAtlas("Entities").findRegion("Ghost_Eyes_Down");
        eyes[3] = ResourceHandler.getInstance().getAtlas("Entities").findRegion("Ghost_Eyes_Right");
        numbers = new TextureRegion[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = ResourceHandler.getInstance().getAtlas("Fonts").findRegion("" + i);
        }
    }



}
