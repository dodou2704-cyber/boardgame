package com.dodou.boardgame.domain.rules;

import com.dodou.boardgame.domain.model.Player;

public interface EndRule {

    int calculatePosition(Player player, int roll);
}