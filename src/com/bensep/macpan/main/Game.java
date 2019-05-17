package com.bensep.macpan.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bensep.macpan.entities.testActor;
import com.bensep.macpan.handlers.GameStateManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bensep.macpan.tesxtures.Animations;
import com.bensep.macpan.tesxtures.Textures;

public class Game implements ApplicationListener {

    public static final String TITLE = "Macpan";
    public static final int V_WIDTH = 320;
    public static final int V_HEIGHT = 240;
    public static final int SCALE = 2;

    public static final float STEP = 1/60f;
    private float accum;

    private SpriteBatch sb;
    private OrthographicCamera cam;
    private OrthographicCamera hudCam;

    private GameStateManager gsm;

    //Testing by Seb - Stage
    ////
    public Stage stage;
    public testActor ta;
    ////

    @Override
    public void create() {

        sb = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

        gsm = new GameStateManager(this);

        //Testing by Seb - Stage
        ////
        stage = new Stage();

        ta = new testActor();
        stage.addActor(ta);
        ////

    }

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(false,width, height);
    }

    @Override
    public void render() {

        accum += Gdx.graphics.getDeltaTime();
        while(accum >= STEP){
            accum -= STEP;
            gsm.update(STEP);
            gsm.render();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public SpriteBatch getSb() {
        return sb;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public OrthographicCamera getHudCam() {
        return hudCam;
    }
}
