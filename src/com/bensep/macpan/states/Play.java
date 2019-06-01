package com.bensep.macpan.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bensep.macpan.gameWorld.PacManMaze;
import com.bensep.macpan.handlers.GameStateManager;

public class Play extends GameState {

    private BitmapFont font = new BitmapFont();
    private PacManMaze pacManMaze;

    public Play(GameStateManager gsm) {
        super(gsm);
        pacManMaze = new PacManMaze();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        pacManMaze.update();
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
        pacManMaze.render(game.getSb());
        //gsm.game().stage.draw();
        ////


        sb.end();
    }

    @Override
    public void dispose() {

    }
}
