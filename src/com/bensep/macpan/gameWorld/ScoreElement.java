package com.bensep.macpan.gameWorld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensep.macpan.textures.Textures;

public class ScoreElement extends WorldPart {

    private int digit;
    private PacManMaze world;
    private TextureRegion[] numbers;

    public ScoreElement(float x, float y, PacManMaze world, int digit) {
        super(x, y, (byte) 0, null);
        this.world = world;
        this.digit = digit;
        numbers = Textures.getInstance().numbers;
    }



    @Override
    public void render(SpriteBatch spriteBatch) {
        if (world.getScore() >= digit) {
            spriteBatch.draw(numbers[world.getScore() / digit % 10], x, y);
        }
    }
}
