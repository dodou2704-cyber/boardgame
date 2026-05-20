package com.dodou.boardgame.infrastructure.dice;

import com.dodou.boardgame.domain.rules.DiceShaker;

import java.util.Random;

public class SingleDiceShaker implements DiceShaker {

    private final Random random = new Random();

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}