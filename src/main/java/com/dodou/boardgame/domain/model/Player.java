package com.dodou.boardgame.domain.model;

public class Player {

    private String name;
    private int position;
    private int homePosition;
    private int endPosition;
    private boolean movingForward;

    public Player(String name,
                  int homePosition,
                  int endPosition,
                  boolean movingForward) {

        this.name = name;
        this.homePosition = homePosition;
        this.endPosition = endPosition;
        this.position = homePosition;
        this.movingForward = movingForward;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getHomePosition() {
        return homePosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public boolean isMovingForward() {
        return movingForward;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}