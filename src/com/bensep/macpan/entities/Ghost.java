package com.bensep.macpan.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.gameWorld.WorldPart;
import com.bensep.macpan.handlers.Personality;
import com.bensep.macpan.myGameLib.Direction;
import com.bensep.macpan.myGameLib.Entity;
import com.bensep.macpan.myGameLib.GameWorld;
import com.bensep.macpan.tesxtures.Textures;

import static com.bensep.macpan.constants.Constants.TILE_SIZE;
import static com.bensep.macpan.constants.Constants.GHOST_PRECISION;

public class Ghost extends Entity {

    private Personality personality;
    private Direction direction;
    private Direction newDirection;
    private State state;
    private State oldState;
    private boolean directionOverwrite;
    private WorldPart currentTile;
    private float speed;
    private float speedBoost;
    private float speedBoostTimer;
    private float speedBuffer;
    private float freezeTimer;
    private float[] options;
    private boolean turn;
    private TextureRegion[] eyes;


    public Ghost(Personality personality, GameWorld gameWorld, float speed) {
        super(personality.getXStartPos(), personality.getYStartPos(), TILE_SIZE, TILE_SIZE, -4, -4, (byte) (OUT1 | OUT2 | OUT3), OUT2, Textures.getInstance().frightened, 1, 1, gameWorld);
        this.personality = personality;
        currentTile = (WorldPart) gameWorld.getTileAt(getCenter().x, center.y);
        direction = Direction.LEFT;
        newDirection = Direction.NONE;
        this.speed = speed;
        options = new float[4];
        freezeTimer = 60;
        state = State.CHASE;
        eyes = Textures.getInstance().eyes;
    }

    @Override
    public void update() {
        if (currentTile != (WorldPart) gameWorld.getTileAt(getCenter().x, center.y)) {
            personality.update(state);
            handleDecision();
        }
        currentTile = (WorldPart) gameWorld.getTileAt(getCenter().x, center.y);
        gameWorld.hitEntity(getCenter(), 1, dmgCollide);
        super.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        switch (state) {
            case FRIGHTENED:
                super.render(spriteBatch);
                return;
            default:
                spriteBatch.draw(personality.getTexture(), x + xTextureOffset, y + yTextureOffset);
            case DEAD:
                switch (direction) {
                    case UP:
                        spriteBatch.draw(eyes[0],x + xTextureOffset, y + yTextureOffset);
                        return;
                    case LEFT:
                        spriteBatch.draw(eyes[1],x + xTextureOffset, y + yTextureOffset);
                        return;
                    case DOWN:
                        spriteBatch.draw(eyes[2],x + xTextureOffset, y + yTextureOffset);
                        return;
                    case RIGHT:
                        spriteBatch.draw(eyes[3],x + xTextureOffset, y + yTextureOffset);
                        return;
                }
        }
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
            while (speedBuffer >= GHOST_PRECISION) {
                handleWalk();
                speedBuffer -= GHOST_PRECISION;
            }
        } else {
            freezeTimer--;
        }
    }

    public void handleWalk() {
        turn = false;
        switch (newDirection) {
            case UP:
                turn = translate(0f, GHOST_PRECISION);
                break;
            case DOWN:
                turn = translate(0f, -GHOST_PRECISION);
                break;
            case LEFT:
                turn = translate(-GHOST_PRECISION, 0f);
                break;
            case RIGHT:
                turn = translate(GHOST_PRECISION, 0f);
                break;
        }
        if (turn) {
            direction = newDirection;
        } else {
            switch (direction) {
                case UP:
                    translate(0f, GHOST_PRECISION);
                    break;
                case DOWN:
                    translate(0f, -GHOST_PRECISION);
                    break;
                case LEFT:
                    translate(-GHOST_PRECISION, 0f);
                    break;
                case RIGHT:
                    translate(GHOST_PRECISION, 0f);
                    break;
            }
        }
    }

    private void handleDecision() {
        int counter = 0;
        if (directionOverwrite) {
            newDirection = direction.getOpposing();
            directionOverwrite = false;
        }else{
            switch (state) {
            case DEAD:
            case CHASE:
            case SCATTER:
                try {
                    for (Direction d : Direction.values()) {
                        if (!direction.opposing(d)) {
                            switch (d) {
                                case UP:
                                    if (!checkCollision(Direction.UP)) {
                                        options[counter] = gameWorld.getTileAt(center.x, center.y + TILE_SIZE).getPos().dst(personality.getTargetTile());
                                    } else options[counter] = -1;
                                    break;
                                case LEFT:
                                    if (!checkCollision(Direction.LEFT)) {
                                        options[counter] = gameWorld.getTileAt(center.x - TILE_SIZE, center.y).getPos().dst(personality.getTargetTile());
                                    } else options[counter] = -1;
                                    break;
                                case DOWN:
                                    if (!checkCollision(Direction.DOWN)) {
                                        options[counter] = gameWorld.getTileAt(center.x, center.y - TILE_SIZE).getPos().dst(personality.getTargetTile());
                                    } else options[counter] = -1;
                                    break;
                                case RIGHT:
                                    if (!checkCollision(Direction.RIGHT)) {
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
                break;
                case FRIGHTENED:
                    newDirection = Direction.getRandom();
                    if (checkCollision(newDirection)||direction.opposing(newDirection)) {
                        newDirection = Direction.UP;
                        while (checkCollision(newDirection)||direction.opposing(newDirection)) {
                            switch (newDirection) {
                                case UP:
                                    newDirection = Direction.LEFT;
                                    break;
                                case LEFT:
                                    newDirection = Direction.DOWN;
                                    break;
                                case DOWN:
                                    newDirection = Direction.RIGHT;
                                    break;
                            }
                        }
                    }

                break;
                case House:
                break;
            }

        }

    }

    private boolean checkCollision(Direction direction) {
        switch (direction) {
            case UP:
                return gameWorld.checkCollide(getCenter().x, center.y + TILE_SIZE, collide);
            case LEFT:
                return gameWorld.checkCollide(getCenter().x - TILE_SIZE, center.y, collide);
            case DOWN:
                return gameWorld.checkCollide(getCenter().x, center.y - TILE_SIZE, collide);
            case RIGHT:
                return gameWorld.checkCollide(getCenter().x + TILE_SIZE, center.y, collide);
        }
        return true;
    }

    public void setSpeed(float speed, float time) {
        if (time == 0f) {
            this.speed = speed;
        } else {
            speedBoost = speed;
            speedBoostTimer = time;
        }
    }

    public void setState(State state) {
        oldState = this.state;
        this.state = state;
        switch (state) {
            case FRIGHTENED:
                speed = ((PacManMaze) gameWorld).getGhostFrightSpeed();
                collide = (byte) (OUT1 | OUT2);
                dmgCollide = IN1;
                break;
            case DEAD:
                speed = 1f;
                collide = (byte) (OUT1);
                dmgCollide = 0;
                break;
            default:
                speed = ((PacManMaze) gameWorld).getGhostSpeed();
                collide = (byte) (OUT1 | OUT2 | OUT3);
                dmgCollide = OUT2;

        }
        switch (state) {
            case SCATTER:
                if (oldState == State.CHASE) {
                    directionOverwrite = true;
                }
                break;
            case FRIGHTENED:
                directionOverwrite = true;
                break;
        }
    }

    @Override
    public void onDeath() {
        ((PacManMaze) gameWorld).freeze(30);
        setState(State.DEAD);
        health = 1;
    }

    public enum State {
        CHASE,
        SCATTER,
        FRIGHTENED,
        DEAD,
        House
    }
}
