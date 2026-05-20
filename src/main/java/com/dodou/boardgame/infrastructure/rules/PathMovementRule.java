package com.dodou.boardgame.infrastructure.rules;

import com.dodou.boardgame.domain.model.Player;

import java.util.List;

public class PathMovementRule {

    public int calculatePathPosition(Player player, int roll) {

        List<Integer> positions =
                player.getPath().positions();

        int currentIndex =
                positions.indexOf(player.getPosition());

        int newIndex = currentIndex + roll;

        if (newIndex >= positions.size()) {
            int overflow = newIndex - (positions.size() - 1);
            newIndex = (positions.size() - 1) - overflow;
        }

        return positions.get(newIndex);
    }
}
