package com.dodou.boardgame.rules;

import com.dodou.boardgame.domain.model.Player;
import com.dodou.boardgame.domain.model.PlayerPath;
import com.dodou.boardgame.infrastructure.rules.PathMovementRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathMovementRuleTest {

    @Test
    void playerMovesForwardOnPath() {

        PlayerPath path =
                new PlayerPath(
                        List.of(1, 2, 3, 4, 5, 6)
                );

        Player player =
                new Player(
                        "Red",
                        1,
                        6,
                        true,
                        path
                );

        PathMovementRule rule =
                new PathMovementRule();

        int result =
                rule.calculatePathPosition(
                        player,
                        3
                );

        assertEquals(4, result);
    }

    @Test
    void playerBouncesBackAtEndOfPath() {

        PlayerPath path =
                new PlayerPath(
                        List.of(1, 2, 3, 4, 5, 6)
                );

        Player player =
                new Player(
                        "Red",
                        5,
                        6,
                        true,
                        path
                );

        player.setPosition(5);

        PathMovementRule rule =
                new PathMovementRule();

        int result =
                rule.calculatePathPosition(
                        player,
                        3
                );

        assertEquals(4, result);
    }
}
