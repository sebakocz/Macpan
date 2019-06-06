package com.bensep.macpan.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.handlers.InputHandler;
import com.bensep.macpan.myGameLib.Direction;
import com.bensep.macpan.myGameLib.Entity;
import com.bensep.macpan.tesxtures.Animations;

import static com.bensep.macpan.constants.Constants.*;

public class PacMan extends Entity {

    private Direction direction;
    private Animation animation;
    private float speed;
    private float speedBoost;
    private float speedBoostTimer;
    private float speedBuffer;
    private float freezeTimer;

    public PacMan(float x, float y, PacManMaze gameWorld, float speed) {
        super(x * TILE_SIZE, y * TILE_SIZE, 8, 8, -4, -4, (byte) (OUT1 | OUT2), (byte) (OUT1 | IN2), null, 1, 1, gameWorld);
        animation = Animations.getInstance().pacMan;
        direction = Direction.LEFT;
        this.speed = speed;
    }

    @Override
    public void update() {
        gameWorld.hitEntity(getCenter(), 1, dmgCollide);
        super.update();
    }

    @Override
    public void updateMovement() {
        if (freezeTimer <= 0) {
            if (speedBoostTimer > 0) {
                speedBoostTimer--;
                speedBuffer += speedBoost;
            }else speedBuffer += speed;

            if (speedBuffer > 0) {
                handleWalk(InputHandler.getInstance().getDirection());
                speedBuffer = 0;
            }
        } else freezeTimer--;
    }

    public void handleWalk(Direction direction) {
        if (direction != this.direction && direction != Direction.NONE) {
            switch (direction) {
                case UP:
                    if (!gameWorld.checkCollide(getCenter().x, center.y + TILE_SIZE, collide)) {
                        cornering(direction);
                    }
                    break;
                case DOWN:
                    if (!gameWorld.checkCollide(getCenter().x, center.y - TILE_SIZE, collide)) {
                        cornering(direction);
                    }
                    break;
                case LEFT:
                    if (!gameWorld.checkCollide(getCenter().x - TILE_SIZE, center.y, collide)) {
                        cornering(direction);
                    }
                    break;
                case RIGHT:
                    if (!gameWorld.checkCollide(getCenter().x + TILE_SIZE, center.y, collide)) {
                        cornering(direction);
                    }
                    break;
            }
        }
        switch (this.direction) {
            case UP:
                translate(0, speedBuffer);
                break;
            case DOWN:
                translate(0, -speedBuffer);
                break;
            case LEFT:
                translate(-speedBuffer, 0);
                break;
            case RIGHT:
                translate(speedBuffer, 0);
                break;
        }

    }

    private void cornering(Direction direction) {
        getCenter();
        if (this.direction.opposing(direction)) {
            this.direction = direction;
        } else {
            this.direction = direction;
            speedBuffer += Math.abs(gameWorld.getTileAt(center.x, center.y).x - x);
            speedBuffer += Math.abs(gameWorld.getTileAt(center.x, center.y).y - y);
            x = gameWorld.getTileAt(center.x, center.y).x;
            y = gameWorld.getTileAt(center.x, center.y).y;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        switch (direction) {
            case UP:
                spriteBatch.draw(animation.getKeyFrame((x + y + 4f), true), x + xTextureOffset, y + yTextureOffset, TILE_SIZE, TILE_SIZE, TILE_SIZE * 2, TILE_SIZE * 2, 1, 1, 0);
                break;
            case DOWN:
                spriteBatch.draw(animation.getKeyFrame((x + y + 4f), true), x + xTextureOffset, y + yTextureOffset, TILE_SIZE, TILE_SIZE, TILE_SIZE * 2, TILE_SIZE * 2, 1, -1, 0);
                break;
            case LEFT:
                spriteBatch.draw(animation.getKeyFrame((x + y + 4f), true), x + xTextureOffset, y + yTextureOffset, TILE_SIZE, TILE_SIZE, TILE_SIZE * 2, TILE_SIZE * 2, 1, 1, 90);
                break;
            case RIGHT:
                spriteBatch.draw(animation.getKeyFrame((x + y + 4f), true), x + xTextureOffset, y + yTextureOffset, TILE_SIZE, TILE_SIZE, TILE_SIZE * 2, TILE_SIZE * 2, 1, -1, 90);
                break;
        }
    }

    @Override
    public boolean translate(float moveX, float moveY) {
        return super.translate(moveX, moveY);
    }

    public void stop(float frames) {
        freezeTimer += frames;
    }

    public void setSpeed(float speed, float time) {
        if (time == 0f) {
            this.speed = speed;
        } else {
            speedBoost = speed;
            speedBoostTimer = time;
        }
    }
}
