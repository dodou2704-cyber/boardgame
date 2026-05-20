package com.dodou.boardgame.domain.model;

public class SnakeOrLadder {

    private final int firstPosition;
    private final int secondPosition;

    public SnakeOrLadder(int firstPosition, int secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public int getSecondPosition() {
        return secondPosition;
    }
}

