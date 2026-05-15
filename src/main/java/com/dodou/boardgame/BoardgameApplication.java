package com.dodou.boardgame;

import com.dodou.boardgame.usecase.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardgameApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardgameApplication.class, args);
    }

    {
        Game game = new Game();
        game.play();

    }
}



