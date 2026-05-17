package com.dodou.boardgame.domain.model;

public class Player {

    private String name;
    private int position;
    private int homePosition;
    private int endPosition;
    private boolean movingForward;
    private PlayerPath path;

    public Player(String name,
                  int homePosition,
                  int endPosition,
                  boolean movingForward,
                  PlayerPath path) {

        this.name = name;
        this.homePosition = homePosition;
        this.endPosition = endPosition;
        this.position = homePosition;
        this.movingForward = movingForward;
        this.path = path;
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

    public PlayerPath getPath() {
        return path;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}