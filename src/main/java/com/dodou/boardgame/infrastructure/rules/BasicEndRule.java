package com.dodou.boardgame.infrastructure.rules;

import com.dodou.boardgame.domain.model.Player;

import java.util.List;

public class BasicEndRule {

    public int calculatePathPosition(Player player, int roll) {

        List<Integer> positions =
                player.getPath().positions();

        int currentIndex =
                positions.indexOf(player.getPosition());

        int newIndex = currentIndex + roll;

        if (newIndex >= positions.size()) {
            return player.getEndPosition();
        }

        return positions.get(newIndex);
    }
}
