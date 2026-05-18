package com.dodou.boardgame.rules;

import com.dodou.boardgame.domain.model.Player;
import com.dodou.boardgame.infrastructure.rules.NoMoveOnHitRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoMoveOnHitRuleTest {

    @Test
    void playerDoesNotMoveWhenLandingOnAnotherPlayer() {

        Player red =
                new Player(
                        "Red",
                        1,
                        36,
                        true,
                        null
                );

        Player blue =
                new Player(
                        "Blue",
                        31,
                        6,
                        false,
                        null
                );

        NoMoveOnHitRule rule =
                new NoMoveOnHitRule();

        int result =
                rule.resolveHit(
                        red,
                        blue,
                        10,
                        31
                );

        assertEquals(10, result);
    }

    @Test
    void playerMovesNormallyWhenNoCollision() {

        Player red =
                new Player(
                        "Red",
                        1,
                        36,
                        true,
                        null
                );

        Player blue =
                new Player(
                        "Blue",
                        31,
                        6,
                        false,
                        null
                );

        NoMoveOnHitRule rule =
                new NoMoveOnHitRule();

        int result =
                rule.resolveHit(
                        red,
                        blue,
                        10,
                        20
                );

        assertEquals(20, result);
    }
}
