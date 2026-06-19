package com.dodou.boardgame.infrastructure.rules;

import com.dodou.boardgame.domain.model.Player;
import com.dodou.boardgame.domain.rules.EndRule;

import java.util.List;

public class ExactEndRule implements EndRule {

    @Override
    public int calculatePosition(Player player, int roll) {

        List<Integer> positions =
                player.getPath().positions();

        int currentIndex =
                positions.indexOf(player.getPosition());

        int newIndex =
                currentIndex + roll;

        if (newIndex >= positions.size()) {
            int overflow =
                    newIndex - (positions.size() - 1);

            newIndex =
                    (positions.size() - 1) - overflow;
        }

        return positions.get(newIndex);
    }
}