package com.dodou.boardgame.usecase;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
import com.dodou.boardgame.infrastructure.dice.FixedDiceShaker;
public class Game {

    public void play() {

        Board board = new Board(25);

        Player red = new Player("Red", 1, 25, true);
        Player blue = new Player("Blue", 25, 1, false);
        Player green = new Player("Green", 1, 25, true);
        Player yellow = new Player("Yellow", 25, 1, false);
        List<Player> players = List.of(
                red,
                blue,
                green,
                yellow
        );


        Map<Player, Integer> turnCounts = new HashMap<>();

        for (Player player : players) {
            turnCounts.put(player, 0);
        }

        int totalTurns = 0;


        DiceShaker diceShaker =
                new FixedDiceShaker(12, 6, 6, 12, 5, 6);
        EndRule endRule = new ExactEndRule();
        HitRule hitRule = new NoMoveOnHitRule();

        WormholeRule wormholeRule = new SimpleWormholeRule();

        List<Wormhole> wormholes = List.of(

        );

        System.out.println("Game");
        System.out.println("Board: rows=5 columns=5");
        System.out.println("Players: Red, Blue, Green and Yellow");
        System.out.println("Dice: Two 6-sided dice");
        System.out.println("End Rule: Exact End");
        System.out.println("Hit Rule: No Move On Hit");
        System.out.println("Teleport Rule: Wormholes enabled");
        System.out.println("Wormholes: 4 <-> 9, 19 <-> 23");
        System.out.println();


        while (true) {

            for (Player player : players) {

                boolean gameWon = takeTurn(
                        player,
                        players,
                        diceShaker,
                        endRule,
                        hitRule,
                        wormholeRule,
                        wormholes,
                        turnCounts,
                        board,
                        totalTurns
                );

                totalTurns++;

                if (gameWon) {
                    System.out.println("Total turns: " + totalTurns);
                    return;
                }

            }


            int redRoll = diceShaker.roll();
            turnCounts.put(red, turnCounts.get(red) + 1);
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
            System.out.println(red.getName() + " turn count: " + turnCounts.get(red));

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

            if (red.getPosition() == red.getEndPosition()) {
                System.out.println(red.getName() + " wins in " + turnCounts.get(red) + " turns.");
                System.out.println("Total turns: " + totalTurns);
                break;
            }

            int blueRoll = diceShaker.roll();
            turnCounts.put(blue, turnCounts.get(blue) + 1);
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
            System.out.println(blue.getName() + " turn count: " + turnCounts.get(blue));

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

            if (blue.getPosition() == blue.getEndPosition()) {
                System.out.println(blue.getName() + " wins in " + turnCounts.get(blue) + " turns.");
                System.out.println("Total turns: " + totalTurns);
                break;
            }
        }
    }

    private Player findOtherPlayer(Player currentPlayer, List<Player> players) {

        for (Player otherPlayer : players) {

            if (otherPlayer != currentPlayer &&
                    otherPlayer.getPosition() == currentPlayer.getPosition()) {

                return otherPlayer;
            }
        }

        return null;
    }

    private boolean takeTurn(Player player,
                             List<Player> players,
                             DiceShaker diceShaker,
                             EndRule endRule,
                             HitRule hitRule,
                             WormholeRule wormholeRule,
                             List<Wormhole> wormholes,
                             Map<Player, Integer> turnCounts,
                             Board board,
                             int totalTurns) {

        int roll = diceShaker.roll();

        turnCounts.put(player, turnCounts.get(player) + 1);

        int newPosition =
                endRule.calculatePosition(
                        player.getPosition(),
                        roll,
                        board.getSize(),
                        player.isMovingForward()
                );
        Player otherPlayer = findOtherPlayer(player, players);

        if (otherPlayer != null) {
            player.setPosition(
                    hitRule.resolveHit(
                            player,
                            otherPlayer,
                            player.getPosition(),
                            newPosition

                    )
            );


        } else {
            player.setPosition(newPosition);
        }
        System.out.println(player.getName() + " rolls " + roll);
        System.out.println(player.getName() + " moves to " + player.getPosition());
        System.out.println(player.getName() + " turn count: " + turnCounts.get(player));

        int beforeWormhole = player.getPosition();

        player.setPosition(
                wormholeRule.resolveWormhole(
                        player.getPosition(),
                        wormholes
                )
        );

        if (player.getPosition() != beforeWormhole) {

            System.out.println(player.getName()
                    + " used a wormhole to "
                    + player.getPosition());
        }

        if (player.getPosition() == player.getEndPosition()) {

            System.out.println(player.getName()
                    + " wins in "
                    + turnCounts.get(player)
                    + " turns.");

            return true;
        }

        return false;
    }

    private List<Player> createPlayers() {

        Player red = new Player("Red", 1, 25, true);
        Player blue = new Player("Blue", 25, 1, false);
        Player green = new Player("Green", 1, 25, true);
        Player yellow = new Player("Yellow", 25, 1, false);

        return List.of(
                red,
                blue,
                green,
                yellow
        );
    }
}








