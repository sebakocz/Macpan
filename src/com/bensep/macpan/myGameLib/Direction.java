package com.bensep.macpan.myGameLib;

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
}
