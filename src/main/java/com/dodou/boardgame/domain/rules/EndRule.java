package com.dodou.boardgame.domain.rules;

public interface EndRule {

    int calculatePosition(int currentPosition,
                          int roll,
                          int boardSize,
                          boolean movingForward);

}
