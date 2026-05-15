package com.dodou.boardgame.domain.rules;

import com.dodou.boardgame.domain.model.Player;


public interface HitRule {
    int resolveHit(Player movingPlayer, Player otherPlayer, int originalPosition, int proposedPosition);
}
