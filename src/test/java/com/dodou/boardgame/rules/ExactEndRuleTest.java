package com.dodou.boardgame.rules;

import com.dodou.boardgame.infrastructure.rules.ExactEndRule;
import org.junit.jupiter.api.Test;

class ExactEndRuleTest {


    @Test
    void playerBouncesBackWhenOverShootingEnd() {
        ExactEndRule rule = new ExactEndRule();

        int newPosition = rule.calculatePosition(
                20,
                12,
                25,
                true
        );
    }


    @Test
    void PlayerMovesNormallyWhenNotOvershooting() {
        ExactEndRule rule = new ExactEndRule();

        int newPosition = rule.calculatePosition(
                10,
                5,
                25,
                true
        );
    }


}
