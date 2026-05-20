package com.dodou.boardgame.usecase;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.dodou.boardgame.domain.model.Board;
import com.dodou.boardgame.domain.model.Player;
import com.dodou.boardgame.domain.rules.DiceShaker;
import com.dodou.boardgame.domain.rules.EndRule;
import com.dodou.boardgame.infrastructure.rules.ExactEndRule;
import com.dodou.boardgame.domain.rules.HitRule;
import com.dodou.boardgame.infrastructure.rules.NoMoveOnHitRule;
import com.dodou.boardgame.domain.model.SnakeOrLadder;
import com.dodou.boardgame.domain.rules.SnakeOrLadderRule;
import com.dodou.boardgame.infrastructure.rules.SimpleSnakeOrLadderRule;
import com.dodou.boardgame.infrastructure.dice.FixedDiceShaker;
import com.dodou.boardgame.domain.model.PlayerPath;
import com.dodou.boardgame.infrastructure.rules.PathMovementRule;
public class Game {

    public void play() {

        Board board = new Board(36);

        List<Player> players = createPlayers();

        Map<Player, Integer> turnCounts = new HashMap<>();

        for (Player player : players) {
            turnCounts.put(player, 0);
        }

        int totalTurns = 0;


        DiceShaker diceShaker =
                new FixedDiceShaker(12, 6, 6, 12, 5, 6);
        EndRule endRule = new ExactEndRule();
        HitRule hitRule = new NoMoveOnHitRule();
        SnakeOrLadderRule SnakeOrLadderRule = new SimpleSnakeOrLadderRule();
        PathMovementRule pathMovementRule = new PathMovementRule();

        List<SnakeOrLadder> snakesAndLadders = List.of(
                new SnakeOrLadder(3, 12),
                new SnakeOrLadder(5, 6),
                new SnakeOrLadder(35, 18),
                new SnakeOrLadder(34, 12)
        );


        System.out.println("Game");
        System.out.println("Board: rows=6 columns=6");
        System.out.println("Players: Red, Blue, Green and Yellow");
        System.out.println("Dice: Two 6-sided dice");
        System.out.println("End Rule: Exact End");
        System.out.println("Hit Rule: No Move On Hit");
        System.out.println("Special Tiles: Snakes and Ladders enabled");
        System.out.println("Ladders: 3 -> 12, 5 -> 6");
        System.out.println("Snakes: 35 -> 18, 34 -> 12");
        System.out.println();


        while (totalTurns < 200) {

            for (Player player : players) {

                boolean gameWon = takeTurn(
                        player,
                        players,
                        diceShaker,
                        endRule,
                        hitRule,
                        SnakeOrLadderRule,
                        pathMovementRule,
                        snakesAndLadders,
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
        }

        System.out.println("Game ended after reaching turn limit.");

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
                             SnakeOrLadderRule snakeOrLadderRule,
                             PathMovementRule pathMovementRule,
                             List<SnakeOrLadder> snakesAndLadders,
                             Map<Player, Integer> turnCounts,
                             Board board,
                             int totalTurns) {

        int roll = diceShaker.roll();

        turnCounts.put(player, turnCounts.get(player) + 1);

        int newPosition =
                pathMovementRule.calculatePathPosition(
                        player,
                        roll
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

        int landedPosition = player.getPosition();

        System.out.println(
                player.getName()
                        + " landed on "
                        + landedPosition
        );

        System.out.println(
                player.getName()
                        + " turn count: "
                        + turnCounts.get(player)
        );

        int beforeMove = player.getPosition();

        player.setPosition(
                snakeOrLadderRule.resolveSnakeOrLadder(
                        player.getPosition(),
                        snakesAndLadders
                )
        );


        if (player.getPosition() != beforeMove) {

            if (player.getPosition() > beforeMove) {
                System.out.println(
                        player.getName()
                                + " climbed a ladder to "
                                + player.getPosition()
                );
            } else {
                System.out.println(
                        player.getName()
                                + " slid down a snake to "
                                + player.getPosition()
                );
            }
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

        PlayerPath redPath = new PlayerPath(List.of(
                1, 2, 3, 4, 5, 6,
                12, 18, 24, 30, 36,
                35, 34, 33, 32, 31
        ));

        PlayerPath bluePath = new PlayerPath(List.of(
                31, 25, 19, 13, 7, 1,
                2, 3, 4, 5, 6
        ));

        PlayerPath greenPath = new PlayerPath(List.of(
                6, 12, 18, 24, 30, 36,
                35, 34, 33, 32, 31
        ));

        PlayerPath yellowPath = new PlayerPath(List.of(
                36, 30, 24, 18, 12, 6,
                5, 4, 3, 2, 1
        ));

        Player red = new Player(
                "Red",
                1,
                36,
                true,
                redPath
        );

        Player blue = new Player(
                "Blue",
                31,
                6,
                false,
                bluePath
        );

        Player green = new Player(
                "Green",
                6,
                31,
                true,
                greenPath
        );

        Player yellow = new Player(
                "Yellow",
                36,
                1,
                false,
                yellowPath
        );

        return List.of(
                red,
                blue,
                green,
                yellow
        );
    }
}








