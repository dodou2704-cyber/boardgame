package com.dodou.boardgame.usecase;


import com.dodou.boardgame.domain.model.Board;
import com.dodou.boardgame.domain.model.Player;
import com.dodou.boardgame.domain.model.PlayerPath;
import com.dodou.boardgame.domain.model.Wormhole;
import com.dodou.boardgame.domain.rules.DiceShaker;
import com.dodou.boardgame.domain.rules.EndRule;
import com.dodou.boardgame.domain.rules.HitRule;
import com.dodou.boardgame.domain.rules.WormholeRule;
import com.dodou.boardgame.infrastructure.dice.FixedDiceShaker;
import com.dodou.boardgame.infrastructure.rules.ExactEndRule;
import com.dodou.boardgame.infrastructure.rules.NoMoveOnHitRule;
import com.dodou.boardgame.infrastructure.rules.PathMovementRule;
import com.dodou.boardgame.infrastructure.rules.SimpleWormholeRule;
import com.dodou.boardgame.infrastructure.rules.SimpleEndRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public void play() {

        Board board = new Board(25);

        List<Player> players = createSmallBoardPlayers();

        Map<Player, Integer> turnCounts = new HashMap<>();

        for (Player player : players) {
            turnCounts.put(player, 0);
        }

        int totalTurns = 0;


        DiceShaker diceShaker =
                new FixedDiceShaker(12, 6, 6, 12, 5, 6);
        EndRule endRule = new SimpleEndRule();
        HitRule hitRule = new NoMoveOnHitRule();
        WormholeRule wormholeRule = new SimpleWormholeRule();
        PathMovementRule pathMovementRule = new PathMovementRule();

        List<Wormhole> wormholes = List.of();

        System.out.println("Game");
        System.out.println("Board: rows=5 columns=5");
        System.out.println("Players: Red and Blue");
        System.out.println("Dice: Two 6-sided dice");
        System.out.println("End Rule: Basic End");
        System.out.println("Hit Rule: No Move On Hit");
        System.out.println("Teleport Rule: Wormholes ignored");
        System.out.println("Wormholes: none");
        System.out.println();


        while (totalTurns < 200) {

            for (Player player : players) {

                boolean gameWon = takeTurn(
                        player,
                        players,
                        diceShaker,
                        endRule,
                        hitRule,
                        wormholeRule,
                        pathMovementRule,
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
                             WormholeRule WormholeRule,
                             PathMovementRule pathMovementRule,
                             List<Wormhole> wormholes,
                             Map<Player, Integer> turnCounts,
                             Board board,
                             int totalTurns) {

        int roll = diceShaker.roll();

        turnCounts.put(player, turnCounts.get(player) + 1);

        int newPosition =
                endRule.calculatePosition(
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
                WormholeRule.resolveWormhole(
                        player.getPosition(),
                        wormholes
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

    private List<Player> createLargeBoardPlayers() {

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

    private List<Player> createSmallBoardPlayers() {

        PlayerPath redPath =
                new PlayerPath(List.of(
                        1, 2, 3, 4, 5,
                        6, 7, 8, 9, 10,
                        11, 12, 13, 14, 15,
                        16, 17, 18, 19, 20,
                        21, 22, 23, 24, 25
                ));

        PlayerPath bluePath =
                new PlayerPath(List.of(
                        25, 24, 23, 22, 21,
                        20, 19, 18, 17, 16,
                        15, 14, 13, 12, 11,
                        10, 9, 8, 7, 6,
                        5, 4, 3, 2, 1
                ));

        Player red =
                new Player(
                        "Red",
                        1,
                        25,
                        true,
                        redPath
                );

        Player blue =
                new Player(
                        "Blue",
                        25,
                        1,
                        false,
                        bluePath
                );

        return List.of(
                red,
                blue
        );
    }


}








