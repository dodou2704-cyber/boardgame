package com.dodou.boardgame.rules;

import com.dodou.boardgame.domain.model.Player;
import com.dodou.boardgame.domain.model.PlayerPath;
import com.dodou.boardgame.infrastructure.rules.ExactEndRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExactEndRuleTest {

    @Test
    void playerBouncesBackWhenOvershootingEnd() {
        PlayerPath path =
                new PlayerPath(List.of(
                        1, 2, 3, 4, 5,
                        6, 7, 8, 9, 10,
                        11, 12, 13, 14, 15,
                        16, 17, 18, 19, 20,
                        21, 22, 23, 24, 25
                ));

        Player player =
                new Player(
                        "Red",
                        20,
                        25,
                        true,
                        path
                );

        player.setPosition(20);

        ExactEndRule rule =
                new ExactEndRule();

        int newPosition =
                rule.calculatePosition(
                        player,
                        12
                );

        assertEquals(18, newPosition);
    }

    @Test
    void playerMovesNormallyWhenNotOvershooting() {
        PlayerPath path =
                new PlayerPath(List.of(
                        1, 2, 3, 4, 5,
                        6, 7, 8, 9, 10,
                        11, 12, 13, 14, 15,
                        16, 17, 18, 19, 20,
                        21, 22, 23, 24, 25
                ));

        Player player =
                new Player(
                        "Red",
                        10,
                        25,
                        true,
                        path
                );

        player.setPosition(10);

        ExactEndRule rule =
                new ExactEndRule();

        int newPosition =
                rule.calculatePosition(
                        player,
                        5
                );

        assertEquals(15, newPosition);
    }
}
