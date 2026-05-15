package com.dodou.boardgame.usecase;

import com.dodou.boardgame.domain.model.Board;
import com.dodou.boardgame.domain.model.Player;
import com.dodou.boardgame.domain.rules.DiceShaker;
import com.dodou.boardgame.infrastructure.dice.DoubleDiceShaker;
import com.dodou.boardgame.domain.rules.EndRule;
import com.dodou.boardgame.infrastructure.rules.ExactEndRule;
import com.dodou.boardgame.domain.rules.HitRule;
import com.dodou.boardgame.infrastructure.rules.NoMoveOnHitRule;
import com.dodou.boardgame.domain.model.Wormhole;
import com.dodou.boardgame.domain.rules.WormholeRule;
import com.dodou.boardgame.infrastructure.rules.SimpleWormholeRule;
import java.util.List;

public class Game {

    public void play() {

        Board board = new Board(25);

        Player red = new Player("Red", 1);
        Player blue = new Player("Blue", 25);

        int redTurns = 0;
        int blueTurns = 0;
        int totalTurns = 0;

        DiceShaker diceShaker = new DoubleDiceShaker();
        EndRule endRule  = new ExactEndRule();
        HitRule hitRule = new NoMoveOnHitRule();

        WormholeRule wormholeRule = new SimpleWormholeRule();

        List<Wormhole> wormholes = List.of(
                new Wormhole(4, 9),
                new Wormhole(19, 23)
        );



        while (true) {

            int redRoll = diceShaker.roll();
            redTurns++;
            totalTurns++;
            int redNewPosition =
                    endRule.calculatePosition(
                            red.getPosition(),
                            redRoll,
                            board.getSize(),
                            true
                    );

            red.setPosition(
                    hitRule.resolveHit(
                            red,
                            blue,
                            red.getPosition(),
                            redNewPosition
                    )
            );

            System.out.println(red.getName() + " rolls " + redRoll);
            System.out.println(red.getName() + " moves to " + red.getPosition());

            int redBeforeWormhole = red.getPosition();

            red.setPosition(
                    wormholeRule.resolveWormhole(
                            red.getPosition(),
                            wormholes
                    )
            );

            if (red.getPosition() != redBeforeWormhole) {
                System.out.println(red.getName()
                        + " used a wormhole to "
                        + red.getPosition());
            }

            if (red.getPosition() >= board.getSize()) {
                System.out.println(red.getName() + " wins in " + redTurns + " turns.");
                System.out.println("Total turns: " + totalTurns);
                break;
            }

            int blueRoll = diceShaker.roll();
            blueTurns++;
            totalTurns++;
            int blueNewPosition =
                    endRule.calculatePosition(
                            blue.getPosition(),
                            blueRoll,
                            board.getSize(),
                            false
                    );

            blue.setPosition(
                    hitRule.resolveHit(
                            blue,
                            red,
                            blue.getPosition(),
                            blueNewPosition
                    )
            );

            System.out.println(blue.getName() + " rolls " + blueRoll);
            System.out.println(blue.getName() + " moves to " + blue.getPosition());

            int blueBeforeWormhole = blue.getPosition();

            blue.setPosition(
                    wormholeRule.resolveWormhole(
                            blue.getPosition(),
                            wormholes
                    )
            );

            if (blue.getPosition() != blueBeforeWormhole) {
                System.out.println(blue.getName()
                        + " used a wormhole to "
                        + blue.getPosition());
            }

            if (blue.getPosition() <= 1) {
                System.out.println(blue.getName() + " wins in " + blueTurns + " turns.");
                System.out.println("Total turns: " + totalTurns);
                break;
            }
        }
    }
}