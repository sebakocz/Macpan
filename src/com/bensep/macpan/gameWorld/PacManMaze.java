package com.bensep.macpan.gameWorld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.entities.Energizer;
import com.bensep.macpan.entities.Ghost;
import com.bensep.macpan.entities.PacMan;
import com.bensep.macpan.entities.Pallet;
import com.bensep.macpan.handlers.Personality;
import com.bensep.macpan.myGameLib.Entity;
import com.bensep.macpan.myGameLib.GameObject;
import com.bensep.macpan.myGameLib.GameWorld;
import com.bensep.macpan.tesxtures.Textures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.bensep.macpan.constants.Constants.*;

public class PacManMaze extends GameWorld {

    private PacMan pacMan;
    private float pacManSpeed;
    private float pacManBoostSpeed;
    private float ghostSpeed;
    private float ghostTunnelSpeed;
    private float ghostFrightSpeed;

    public PacManMaze() {
        super(WORLD_WIDTH, WORLD_HEIGHT, TILE_SIZE);
        loadGrid();
        pacManSpeed = .8f;
        pacManBoostSpeed = .9f;
        ghostSpeed = .75f;
        ghostTunnelSpeed = .4f;
        ghostFrightSpeed = .5f;
        pacMan = new PacMan(13.5f, 9, this, .8f);
        entities.add(pacMan);
        entities.add(new Ghost(new Personality(this), this, .75f));
    }

    public void loadGrid() {
        try {
            Scanner scanner = new Scanner(new File("res/Level/Level1Maze"));
            String tile;
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                for (int x = 0; x < WORLD_WIDTH; x++) {
                    tile = scanner.next();
                    switch (tile) {
                        case "74":
                            worldGrid[x][y] = new RestrictedWorldPart(x, y, (byte) 0, Textures.getInstance().empty,(byte) (Entity.OUT1 | Entity.OUT2 | Entity.OUT3));
                            break;
                        case "75":
                            worldGrid[x][y] = new BoostWorldPart(x, y, (byte) 0, Textures.getInstance().empty, this);
                            break;
                        case "73":
                            setGridAt(x,y,false, Textures.getInstance().empty);
                            break;
                        case "72":
                            worldGrid[x][y] = new WorldPart(x, y, GameObject.IN2, Textures.getInstance().barrier);
                            break;
                        default:
                            setGridAt(x,y,true, Textures.getInstance().maze[Character.getNumericValue(tile.charAt(0))][Character.getNumericValue(tile.charAt(1))]);
                            break;
                    }
                }
            }
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                for (int x = 0; x < WORLD_WIDTH; x++) {
                    switch (scanner.next()) {
                        case "1":
                            spawnEntity(new Pallet(x,y,this));
                            break;
                        case "2":
                            spawnEntity(new Energizer(x,y,this));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                worldGrid[x][y].render(spriteBatch);
            }
        }
        entities.forEach(entity -> entity.render(spriteBatch));
    }

    public void setGridAt(int x, int y, boolean collide, TextureRegion textureRegion) {
        if (collide)
            worldGrid[x][y] = new WorldPart(x, y, GameObject.IN1, textureRegion);
        else
            worldGrid[x][y] = new WorldPart(x, y, (byte) 0, textureRegion);
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public float getPacManSpeed() {
        return pacManSpeed;
    }

    public float getPacManBoostSpeed() {
        return pacManBoostSpeed;
    }

    public float getGhostSpeed() {
        return ghostSpeed;
    }

    public float getGhostTunnelSpeed() {
        return ghostTunnelSpeed;
    }

    public float getGhostFrightSpeed() {
        return ghostFrightSpeed;
    }
}
