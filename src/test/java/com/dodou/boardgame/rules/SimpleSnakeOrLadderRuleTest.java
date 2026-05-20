package com.dodou.boardgame.rules;

import com.dodou.boardgame.domain.model.SnakeOrLadder;
import com.dodou.boardgame.infrastructure.rules.SimpleSnakeOrLadderRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleSnakeOrLadderRuleTest {

    @Test
    void playerMovesWhenLandingOnSnakeOrLadder() {

        SnakeOrLadder snakeOrLadder =
                new SnakeOrLadder(4, 9);

        SimpleSnakeOrLadderRule rule =
                new SimpleSnakeOrLadderRule();

        int result =
                rule.resolveSnakeOrLadder(
                        4,
                        List.of(snakeOrLadder)
                );

        assertEquals(9, result);
    }

    @Test
    void playerDoesNotMoveWhenNoSnakeOrLadderExists() {

        SnakeOrLadder snakeOrLadder =
                new SnakeOrLadder(4, 9);

        SimpleSnakeOrLadderRule rule =
                new SimpleSnakeOrLadderRule();

        int result =
                rule.resolveSnakeOrLadder(
                        7,
                        List.of(snakeOrLadder)
                );

        assertEquals(7, result);
    }
}