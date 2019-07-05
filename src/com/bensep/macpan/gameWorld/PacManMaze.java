package com.bensep.macpan.gameWorld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.entities.Energizer;
import com.bensep.macpan.entities.Ghost;
import com.bensep.macpan.entities.PacMan;
import com.bensep.macpan.entities.Dot;
import com.bensep.macpan.handlers.*;
import com.bensep.macpan.myGameLib.Direction;
import com.bensep.macpan.myGameLib.GameObject;
import com.bensep.macpan.myGameLib.GameWorld;
import com.bensep.macpan.textures.Textures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.bensep.macpan.constants.Constants.*;

public class PacManMaze extends GameWorld {

    private PacMan pacMan;
    private Ghost[] ghosts;
    private float pacManSpeed;
    private float pacManBoostSpeed;
    private float ghostSpeed;
    private float ghostTunnelSpeed;
    private float ghostFrightSpeed;
    private int freeze;
    private int releaseState;
    private SetDirectionPart[] releaseMechanism;

    private int deadCounter;
    private boolean startAnimation;


    private float dotTimer;
    private float dotTimerMax = 240;
    private int dotCounter = 0;
    public int score = 0;

    public PacManMaze() {
        super(WORLD_WIDTH, WORLD_HEIGHT, TILE_SIZE);
        releaseState = 0;
        releaseMechanism = new SetDirectionPart[6];
        loadGrid();
        pacManSpeed = .8f;
        pacManBoostSpeed = .9f;
        ghostSpeed = .75f;
        ghostTunnelSpeed = .4f;
        ghostFrightSpeed = .5f;
        freeze = 0;
        pacMan = new PacMan(13.5f, 9, this, .8f);
        entities.add(pacMan);
        ghosts = new Ghost[4];
        ghosts[0] = new Ghost(new Blinky(this), this, .75f);
        ghosts[1] = new Ghost(new Pinky(this), this, .75f);
        ghosts[2] = new Ghost(new Inky(this), this, .75f);
        ghosts[3] = new Ghost(new Clyde(this), this, .75f);
        for (int i = 0; i < 4; i++) {
            entities.add(ghosts[i]);
        }

        startAnimation = true;
        deadCounter = -1;
    }

    public int getScore() {
        return score;
    }

    public void loadGrid() {
        try {
            Scanner scanner = new Scanner(new File("res/Level/Level1Maze"));
            String tile;
            int scoreDigit = 10000;
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                for (int x = 0; x < WORLD_WIDTH; x++) {
                    tile = scanner.next();
                    switch (tile) {
                        case "74":
                            worldGrid[x][y] = new RestrictedWorldPart(x, y, Textures.getInstance().empty,GameObject.OUT3);
                            break;
                        case "75":
                            worldGrid[x][y] = new BoostWorldPart(x, y, (byte) 0, Textures.getInstance().empty, this);
                            break;
                        case "73":
                            setGridAt(x,y,false, Textures.getInstance().empty);
                            break;
                        case "72":
                            worldGrid[x][y] = new SetDirectionPart(x, y, Textures.getInstance().barrier, (GameObject.OUT2), Direction.UP);
                            break;
                        case "80":
                            worldGrid[x][y] = new SetDirectionPart(x, y, Textures.getInstance().empty, (GameObject.OUT2), Direction.UP);
                            break;
                        case "81":
                            worldGrid[x][y] = new SetDirectionPart(x, y, Textures.getInstance().empty, (GameObject.OUT2), Direction.DOWN);
                            break;
                        case "82":
                            worldGrid[x][y] = new SetDirectionPart(x, y, Textures.getInstance().empty, (GameObject.OUT2), Direction.LEFT);
                            break;
                        case "83":
                            worldGrid[x][y] = new SetDirectionPart(x, y, Textures.getInstance().empty, (GameObject.OUT2), Direction.RIGHT);
                            break;
                        case "84":
                            worldGrid[x][y] = new SetDirectionPart(x, y, Textures.getInstance().empty, (GameObject.OUT2), Direction.NONE);
                            break;
                        case "90":
                            worldGrid[x][y] = new ScoreElement(x, y, this, scoreDigit);
                            scoreDigit /= 10;
                            break;
                        default:
                            setGridAt(x,y,true, Textures.getInstance().maze[Character.getNumericValue(tile.charAt(0))][Character.getNumericValue(tile.charAt(1))]);
                            break;
                    }
                }
            }
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                for (int x = 0; x < WORLD_WIDTH; x++) {
                    switch (scanner.next()) {
                        case "1":
                            spawnEntity(new Dot(x,y,this));
                            break;
                        case "2":
                            spawnEntity(new Energizer(x,y,this));
                    }
                }
            }
            for (int i = 0; i < 6; i++) {
                releaseMechanism[i] = (SetDirectionPart) worldGrid[11 + i][18];
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
        if (deadCounter >= 0) {
            deadCounter++;
            freeze++;
        }
        entities.forEach(entity -> {
            if (entity.getClass()!=Ghost.class||deadCounter<0)
            entity.render(spriteBatch);
        });
    }

    @Override
    public void update() {
        if (freeze <= 0) {
            super.update();
            dotTimer++;
            checkReleaseGhost();
        } else {
            freeze--;
        }
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

    public void setGhostState(Ghost.State state) {
        for (int i = 0; i < 4; i++) {
            ghosts[i].setState(state, 600);
        }
    }

    public void freeze(int frames) {
        freeze += frames;
    }

    public Ghost getGhost(int index){
        return ghosts[index];
    }

    public void dotEaten(){
        //add Score //reset Timer //add dot counter

        score += 10;
        dotTimer = 0;
        dotCounter++;

    }

    private void checkReleaseGhost(){
        if (dotTimer >= dotTimerMax && releaseState < 2) {
            releaseGhost();
        } else if (dotCounter >= 30 && releaseState == 0){ releaseGhost();}
        else if (dotCounter >= 60 && releaseState == 1) releaseGhost();
    }

    public void releaseGhost() {
        dotCounter = 0;
        dotTimer = 0;
        switch (releaseState) {
            case 0:
                releaseMechanism[0].setDirection(Direction.RIGHT);
                releaseMechanism[1].setDirection(Direction.RIGHT);
                releaseMechanism[3].setDirection(Direction.UP);
                break;
            case 1:
                releaseMechanism[5].setDirection(Direction.LEFT);
                releaseMechanism[4].setDirection(Direction.LEFT);
                releaseMechanism[2].setDirection(Direction.UP);
                releaseMechanism[3].setDirection(Direction.NONE);
                break;
            default:
                System.out.println("This should not happen");
        }
        releaseState++;
    }

    public void pacManDied() {
        deadCounter++;
    }
}
