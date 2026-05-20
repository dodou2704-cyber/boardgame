package com.dodou.boardgame.infrastructure.rules;

import com.dodou.boardgame.domain.model.SnakeOrLadder;
import com.dodou.boardgame.domain.rules.SnakeOrLadderRule;

import java.util.List;


public class SimpleSnakeOrLadderRule implements SnakeOrLadderRule {

    @Override
    public int resolveSnakeOrLadder(int position, List<SnakeOrLadder> wormholes) {

        for (SnakeOrLadder wormhole : wormholes) {

            if (position == wormhole.firstPosition()) {
                return wormhole.secondPosition();

            }

            if (position == wormhole.secondPosition()) {
                return wormhole.firstPosition();
            }
        }


        return position;
    }


}
