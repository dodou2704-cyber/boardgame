package com.dodou.boardgame.domain.rules;

import com.dodou.boardgame.domain.model.SnakeOrLadder;


import java.util.List;

public interface SnakeOrLadderRule {

    int resolveSnakeOrLadder(
            int position,
            List<SnakeOrLadder> wormholes
    );


}
