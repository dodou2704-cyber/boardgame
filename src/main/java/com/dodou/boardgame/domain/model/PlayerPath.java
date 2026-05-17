package com.dodou.boardgame.domain.model;


import java.util.List;

public class PlayerPath {

    private final List<Integer> positions;

    public  PlayerPath(List<Integer> positions) {
        this.positions = positions;
    }

    public List<Integer> getPositions() {
        return positions;
    }



}
