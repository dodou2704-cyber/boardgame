package com.dodou.boardgame.usecase;

import com.dodou.boardgame.domain.model.Board;
import com.dodou.boardgame.domain.model.Player;
import com.dodou.boardgame.domain.rules.DiceShaker;
import com.dodou.boardgame.infrastructure.dice.DoubleDiceShaker;
import com.dodou.boardgame.domain.rules.EndRule;
import com.dodou.boardgame.infrastructure.rules.ExactEndRule;


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

        while (true) {

            int redRoll = diceShaker.roll();
            redTurns++;
            totalTurns++;
            red.setPosition(
                    endRule.calculatePosition(
                            red.getPosition(),
                            redRoll,
                            board.getSize(),
                            true
                    )
            );

            System.out.println(red.getName() +
                    " rolls " + redRoll);

            System.out.println(red.getName() +
                    " moves to " + red.getPosition());

            if (red.getPosition() >= board.getSize()) {
                System.out.println(red.getName() + " wins in " + redTurns + " turns.");
                System.out.println("Total turns: " + totalTurns);
                break;
            }

            int blueRoll = diceShaker.roll();
            blueTurns++;
            totalTurns++;
            blue.setPosition(
                    endRule.calculatePosition(
                            blue.getPosition(),
                            blueRoll,
                            board.getSize(),
                            false
                    )
            );

            System.out.println(blue.getName() +
                    " rolls " + blueRoll);

            System.out.println(blue.getName() +
                    " moves to " + blue.getPosition());

            if (blue.getPosition() <= 1) {
                System.out.println(blue.getName() + " wins in " + blueTurns + " turns.");
                System.out.println("Total turns: " + totalTurns);
                break;
            }
        }
    }
}