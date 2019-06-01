package com.bensep.macpan.handlers;

import com.badlogic.gdx.InputAdapter;
import com.bensep.macpan.myGameLib.Direction;

import java.util.Stack;

import static com.bensep.macpan.constants.Constants.*;
import static com.bensep.macpan.myGameLib.Direction.*;

public class InputHandler extends InputAdapter {

    private static InputHandler instance;
    private Direction direction;

    private InputHandler() {
        direction = NONE;
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
                direction = UP;
                break;
            case MOVE_DOWN:
                direction = DOWN;
                break;
            case MOVE_LEFT:
                direction = LEFT;
                break;
            case MOVE_RIGHT:
                direction = RIGHT;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case MOVE_UP:
                if (direction == UP) direction = NONE;
                break;
            case MOVE_DOWN:
                if (direction == DOWN) direction = NONE;
                break;
            case MOVE_LEFT:
                if (direction == LEFT) direction = NONE;
                break;
            case MOVE_RIGHT:
                if (direction == RIGHT) direction = NONE;
                break;
        }
        return true;
    }

    public Direction getDirection() {
        return direction;
    }
}
