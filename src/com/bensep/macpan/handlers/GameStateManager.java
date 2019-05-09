package com.bensep.macpan.handlers;

import com.bensep.macpan.main.Game;
import com.bensep.macpan.states.GameState;

import java.util.Stack;

public class GameStateManager {

    private Game game;

    private Stack<GameState> gameStates;

    public static final int PLAY = 1;

    public GameStateManager(Game game){
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public Game game() {
        return game;
    }

    public void update(float dt){
        gameStates.peek().update(dt);
    }

    public void render(){
        gameStates.peek().render();
    }

    private GameState getState(int state){
        if(state == PLAY) return new Play(this);
        return null;
    }

    public void setState(int state){
        popState();
        pushState();
    }

    public void pushState(int state){
        gameState.push()
    }
}
