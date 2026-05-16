package com.dodou.boardgame.infrastructure.dice;


import com.dodou.boardgame.domain.rules.DiceShaker;

public class FixedDiceShaker implements DiceShaker {

    private final int[] rolls;
    private int currentIndex = 0;

    public FixedDiceShaker(int... rolls) {
        this.rolls = rolls;
    }

    @Override
    public int roll() {
        int value = rolls[currentIndex];

        currentIndex++;

        if (currentIndex >= rolls.length) {
            currentIndex = 0;
        }

        return value;
    }


}
