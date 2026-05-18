package com.dodou.boardgame.rules;

import com.dodou.boardgame.domain.model.Wormhole;
import com.dodou.boardgame.infrastructure.rules.SimpleWormholeRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleWormholeRuleTest {

    @Test
    void playerTeleportsWhenLandingOnWormhole() {

        Wormhole wormhole =
                new Wormhole(4, 9);

        SimpleWormholeRule rule =
                new SimpleWormholeRule();

        int result =
                rule.resolveWormhole(
                        4,
                        List.of(wormhole)
                );

        assertEquals(9, result);
    }

    @Test
    void playerDoesNotTeleportWhenNoWormholeExists() {

        Wormhole wormhole =
                new Wormhole(4, 9);

        SimpleWormholeRule rule =
                new SimpleWormholeRule();

        int result =
                rule.resolveWormhole(
                        7,
                        List.of(wormhole)
                );

        assertEquals(7, result);
    }
}