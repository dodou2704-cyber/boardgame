package com.dodou.boardgame.infrastructure.rules;

import com.dodou.boardgame.domain.model.Player;
import com.dodou.boardgame.domain.rules.HitRule;


public class NoMoveOnHitRule implements HitRule {

    @Override
    public int resolveHit(Player movingPlayer, Player otherPlayer, int originalPosition, int proposedPosition) {

        if (proposedPosition == otherPlayer.getPosition()) {
            System.out.println(movingPlayer.getName() + " hit " + otherPlayer.getName()
                    + " at position " + proposedPosition);
            return originalPosition;
        }

        return proposedPosition;
    }
}
