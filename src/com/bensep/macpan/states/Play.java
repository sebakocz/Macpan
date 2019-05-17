package com.bensep.macpan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bensep.macpan.gameWorld.PacManGrid;
import com.bensep.macpan.handlers.GameStateManager;

public class Play extends GameState {

    private BitmapFont font = new BitmapFont();
    private PacManGrid pacManGrid;

    public Play(GameStateManager gsm) {
        super(gsm);
        pacManGrid = new PacManGrid();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        pacManGrid.update();
    }

    @Override
    public void render() {
        /*
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        font.draw(sb, "play state", 100, 100);
        sb.end();
        */

        //Gdx.gl.glClearColor(1, 1, 1, 1);
        //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        sb.begin();

        //font.draw(sb, "Hello World", 200, 200);

        //Testing by Seb - Stage
        ////
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        pacManGrid.render(game.getSb());
        //gsm.game().stage.draw();
        ////


        sb.end();
    }

    @Override
    public void dispose() {

    }
}
