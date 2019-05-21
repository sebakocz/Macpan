package com.bensep.macpan.handlers;

import com.badlogic.gdx.InputAdapter;
import com.bensep.macpan.myGameLib.Direction;

import java.util.Stack;

import static com.bensep.macpan.constants.Constants.*;
import static com.bensep.macpan.myGameLib.Direction.*;

public class InputHandler extends InputAdapter {

    private Stack<Direction> directions;
    private static InputHandler instance;

    private InputHandler() {
        directions = new Stack<>();
    }

    public static InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandler();
        }
        return instance;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case MOVE_UP:
                directions.remove(UP);
                directions.add(UP);
                break;
            case MOVE_DOWN:
                directions.remove(DOWN);
                directions.add(DOWN);
                break;
            case MOVE_LEFT:
                directions.remove(LEFT);
                directions.add(LEFT);
                break;
            case MOVE_RIGHT:
                directions.remove(RIGHT);
                directions.add(RIGHT);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case MOVE_UP:
                directions.remove(UP);
                break;
            case MOVE_DOWN:
                directions.remove(DOWN);
                break;
            case MOVE_LEFT:
                directions.remove(LEFT);
                break;
            case MOVE_RIGHT:
                directions.remove(RIGHT);
                break;
        }
        return true;
    }

    public Stack<Direction> getDirections() {
        return directions;
    }
}
