package com.company;

import com.piu.engine.Game;
import com.piu.game.GameScenarioHandler;

public class Main {

    public static void main(String[] args) {
        Game g = new Game(new GameScenarioHandler());
        g.start();

    }
}
