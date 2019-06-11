package com.bensep.macpan.myGameLib;

import com.badlogic.gdx.math.MathUtils;

public enum Direction {
    UP, LEFT, DOWN, RIGHT, NONE;

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean opposing(Direction direction) {
        switch (direction) {
            case UP:
                return this == DOWN;
            case DOWN:
                return this == UP;
            case LEFT:
                return this == RIGHT;
            case RIGHT:
                return this == LEFT;
        }
        return false;
    }

    public Direction getOpposing() {
        switch (this) {
            case UP:
                return DOWN;
            case LEFT:
                return RIGHT;
            case DOWN:
                return UP;
            case RIGHT:
                return LEFT;
        }
        return NONE;
    }

    public static Direction getRandom() {
        switch (MathUtils.random(3)) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            case 2:
                return LEFT;
            case 3:
                return RIGHT;

        }
        return NONE;
    }
}
