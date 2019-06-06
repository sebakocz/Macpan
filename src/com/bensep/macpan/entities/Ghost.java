package com.bensep.macpan.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bensep.macpan.gameWorld.WorldPart;
import com.bensep.macpan.handlers.Personality;
import com.bensep.macpan.myGameLib.Direction;
import com.bensep.macpan.myGameLib.Entity;
import com.bensep.macpan.myGameLib.GameWorld;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;

public class Ghost extends Entity {

    private Personality personality;
    private Direction direction;
    private Direction newDirection;
    private State state;
    private WorldPart currentTile;
    private float speed;
    private float speedBoost;
    private float speedBoostTimer;
    private float speedBuffer;
    private float freezeTimer;
    private float[] options;
    private boolean turn;

    public Ghost(Personality personality, GameWorld gameWorld, float speed) {
        super(personality.getXStartPos(), personality.getYStartPos(), TILE_SIZE, TILE_SIZE, -4, -4, (byte) (OUT1 | OUT2), OUT2, null, 1, 1, gameWorld);
        this.personality = personality;
        currentTile = (WorldPart) gameWorld.getTileAt(getCenter().x, center.y);
        direction = Direction.LEFT;
        newDirection = Direction.NONE;
        this.speed = speed;
        options = new float[4];
    }

    @Override
    public void update() {
        if (currentTile != (WorldPart) gameWorld.getTileAt(getCenter().x, center.y)) {
            personality.update();
            handleDecision();
        }
        currentTile = (WorldPart) gameWorld.getTileAt(getCenter().x, center.y);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(personality.getTexture(direction), x + xTextureOffset, y + yTextureOffset);
    }

    @Override
    public void updateMovement() {
        if (freezeTimer <= 0) {
            if (speedBoostTimer > 0) {
                speedBoostTimer--;
                speedBuffer += speedBoost;
            } else {
                speedBuffer += speed;
            }
            while (speedBuffer >= 1f) {
                handleWalk();
                speedBuffer--;
            }
        }
    }

    public void handleWalk() {
        turn = false;
        switch (newDirection) {
            case UP:
                turn = translate(0f, 1f);
                break;
            case DOWN:
                turn = translate(0f, -1f);
                break;
            case LEFT:
                turn = translate(-1f, 0f);
                break;
            case RIGHT:
                turn = translate(1f, 0f);
                break;
        }
        if (turn) {
            direction = newDirection;
        } else {
            switch (direction) {
                case UP:
                    translate(0f, 1f);
                    break;
                case DOWN:
                    translate(0f, -1f);
                    break;
                case LEFT:
                    translate(-1f, 0f);
                    break;
                case RIGHT:
                    translate(1f, 0f);
                    break;
            }
        }
    }

    private void handleDecision() {
        int counter = 0;
        try {
            for (Direction d : Direction.values()) {
                if (!direction.opposing(d)) {
                    switch (d) {
                        case UP:
                            if (!gameWorld.checkCollide(getCenter().x, center.y + TILE_SIZE, collide)) {
                                options[counter] = gameWorld.getTileAt(center.x, center.y + TILE_SIZE).getPos().dst(personality.getTargetTile());
                            } else options[counter] = -1;
                            break;
                        case LEFT:
                            if (!gameWorld.checkCollide(getCenter().x - TILE_SIZE, center.y, collide)) {
                                options[counter] = gameWorld.getTileAt(center.x - TILE_SIZE, center.y).getPos().dst(personality.getTargetTile());
                            } else options[counter] = -1;
                            break;
                        case DOWN:
                            if (!gameWorld.checkCollide(getCenter().x, center.y - TILE_SIZE, collide)) {
                                options[counter] = gameWorld.getTileAt(center.x, center.y - TILE_SIZE).getPos().dst(personality.getTargetTile());
                            } else options[counter] = -1;
                            break;
                        case RIGHT:
                            if (!gameWorld.checkCollide(getCenter().x + TILE_SIZE, center.y, collide)) {
                                options[counter] = gameWorld.getTileAt(center.x + TILE_SIZE, center.y).getPos().dst(personality.getTargetTile());
                            } else options[counter] = -1;
                            break;
                    }
                } else options[counter] = -1;
                counter++;
            }
            float bestOption = 365f;
            for (counter = 0; counter < 4; counter++) {
                if (options[counter] < bestOption && options[counter] != -1) {
                    bestOption = options[counter];
                    switch (counter) {
                        case 0:
                            newDirection = Direction.UP;
                            break;
                        case 1:
                            newDirection = Direction.LEFT;
                            break;
                        case 2:
                            newDirection = Direction.DOWN;
                            break;
                        case 3:
                            newDirection = Direction.RIGHT;
                            break;
                    }
                }
            }
        } catch (Exception ignored) {}
    }

    public void setSpeed(float speed, float time) {
        if (time == 0f) {
            this.speed = speed;
        } else {
            speedBoost = speed;
            speedBoostTimer = time;
        }
    }

    public enum State {
        CHASE,
        SCATTER,
        FRIGHTENED,
        House
    }
}
