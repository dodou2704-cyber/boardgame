package com.dodou.boardgame.domain.model;

public class Player {

    private String name;
    private int position;
    private int homePosition;
    private int endPosition;


    public Player(String name,
                  int homePosition,
                  int endPosition) {

        this.name = name;
        this.homePosition = homePosition;
        this.endPosition = endPosition;
        this.position = homePosition;
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

    public void setPosition(int position) {
        this.position = position;
    }
}
