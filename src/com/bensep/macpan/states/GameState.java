package com.bensep.macpan.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bensep.macpan.main.Game;

public abstract class GameState {

    protected  GameStateManager gsm;
    protected Game game;

    protected SpriteBatch sb;
    protected OrthographicCamera cam;
    protected OrthographicCamera hudCam;

    protected GameState(GameStateManager gsm){

        this.gsm = gsm;
        game = gsm.game();
        sb = game.getSb();
        cam = game.getCam();
        hudCam = game.getHudCam();
    }

    public abstract  void handleInput();
    public abstract void update(float dt);
    public abstract void render();
    public abstract void dispose();

}
