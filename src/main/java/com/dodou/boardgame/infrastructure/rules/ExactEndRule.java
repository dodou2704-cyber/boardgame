package com.dodou.boardgame.infrastructure.rules;

import com.dodou.boardgame.domain.rules.EndRule;

public class ExactEndRule implements EndRule {

    @Override
    public int calculatePosition(int currentPosition,
                                 int roll,
                                 int boardSize,
                                 boolean movingForward) {

        int newPosition;

        if (movingForward) {

            newPosition = currentPosition + roll;

            if (newPosition > boardSize) {

                int overflow = newPosition - boardSize;
                newPosition = boardSize - overflow;
            }

        } else {

            newPosition = currentPosition - roll;

            if (newPosition < 1) {

                int overflow = 1 - newPosition;
                newPosition = 1 + overflow;
            }
        }

        return newPosition;
    }
}
