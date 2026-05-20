package com.dodou.boardgame.infrastructure.dice;

import com.dodou.boardgame.domain.rules.DiceShaker;

import java.util.Random;

public class DoubleDiceShaker implements DiceShaker {
    private final Random random = new Random();

    @Override
    public int roll() {
        int firstDie = random.nextInt(6) + 1;
        int secondDie = random.nextInt(6) + 1;

        return firstDie + secondDie;
    }


}
