package com.dodou.boardgame.domain.rules;

import com.dodou.boardgame.domain.model.Wormhole;


import java.util.List;

public interface WormholeRule {

    int resolveWormhole(
            int position,
            List<Wormhole> wormholes
    );


}
