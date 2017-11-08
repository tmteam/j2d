package com.company;

import com.genetics.CompactDMXGenomRepresentation;
import com.genetics.Genom;
import com.neural.Perceptron;
import com.neural.PerceptronSettings;
import com.piu.engine.Game;
import com.piu.game.GameScenarioHandler;
import com.piu.game.Pius.Piu;
import com.piu.game.Pius.PiuFactory;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            double[] genome =  PerceptronSettings.createRandom(new int[]{19, 19, 8,3}).toGenom();
            String representation =  new CompactDMXGenomRepresentation(genome, 20).toString();
            System.out.println(representation);
        }
        Game g = new Game(new GameScenarioHandler());
        g.start();

    }
}
