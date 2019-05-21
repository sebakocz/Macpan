package com.bensep.macpan.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.handlers.InputHandler;
import com.bensep.macpan.myGameLib.Direction;
import com.bensep.macpan.myGameLib.Entity;
import com.bensep.macpan.myGameLib.GameWorld;
import com.bensep.macpan.tesxtures.Animations;

import static com.bensep.macpan.constants.Constants.*;

public class PacMan extends Entity {

    private Direction direction;
    private Animation animation;

    public PacMan(float x, float y, GameWorld gameWorld) {
        super(x * TILE_SIZE, y * TILE_SIZE, 8, 8, -4, -4, (byte) (OUT1 | OUT2), (byte) (OUT1 | IN2), null, 1, 1, gameWorld);
        animation = Animations.getInstance().pacMan;
        direction = Direction.UP;
    }

    @Override
    public void updateMovement() {
        if (!InputHandler.getInstance().getDirections().empty()) {
            direction = InputHandler.getInstance().getDirections().peek();
        }
        switch (direction) {
            case UP:
                translate(0, 1);
                break;
            case DOWN:
                translate(0, -1);
                break;
            case LEFT:
                translate(-1, 0);
                break;
            case RIGHT:
                translate(1, 0);
                break;
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        switch (direction) {
            case UP:
                spriteBatch.draw(animation.getKeyFrame((x + y), true), x + xTextureOffset, y + yTextureOffset, TILE_SIZE, TILE_SIZE, TILE_SIZE * 2, TILE_SIZE * 2, 1, 1, 0);
                break;
            case DOWN:
                spriteBatch.draw(animation.getKeyFrame((x + y), true), x + xTextureOffset, y + yTextureOffset, TILE_SIZE, TILE_SIZE, TILE_SIZE * 2, TILE_SIZE * 2,1,1, 180);
                break;
            case LEFT:
                spriteBatch.draw(animation.getKeyFrame((x + y), true), x + xTextureOffset, y + yTextureOffset, TILE_SIZE, TILE_SIZE, TILE_SIZE * 2, TILE_SIZE * 2,1,1, 90);
                break;
            case RIGHT:
                spriteBatch.draw(animation.getKeyFrame((x + y), true), x + xTextureOffset, y + yTextureOffset, TILE_SIZE, TILE_SIZE, TILE_SIZE * 2, TILE_SIZE * 2, 1, 1, 270);
                break;
        }
    }

    @Override
    public boolean translate(float x, float y) {
        return super.translate(x, y);
    }
}
