package com.dodou.boardgame.infrastructure.rules;

import com.dodou.boardgame.domain.model.Wormhole;
import com.dodou.boardgame.domain.rules.WormholeRule;

import java.util.List;


public class SimpleWormholeRule implements WormholeRule {

    @Override
    public int resolveWormhole(int position, List<Wormhole> wormholes) {

        for (Wormhole wormhole : wormholes) {

            if (position == wormhole.getFirstPosition()) {
                return wormhole.getSecondPosition();

            }

            if (position == wormhole.getSecondPosition()) {
                return wormhole.getFirstPosition();
            }
        }

            return position;
        }


}
