package com.dodou.boardgame.infrastructure.rules;

import com.dodou.boardgame.domain.rules.EndRule;

public class SimpleEndRule implements EndRule {
    @Override
    public int calculatePosition(int currentPosition,
                                 int roll,
                                 int boardSize,
                                 boolean movingForward) {
        if (movingForward) {
            return currentPosition + roll;

        }
        return currentPosition - roll;
    }
}
