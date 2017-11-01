package com.tutorial.game.GameObjects.Pius;

import java.util.Random;

public class RandomBrain implements  IPivaBrain {
    Random rnd = new Random();
    int tickCount;
    double throttleLevel = 0;
    double breakLevel = 0;
    double turnLevel = 0;
    @Override
    public void tick(CurrentInput input) {
        if(tickCount >= 1000){
            tickCount = 0;
            throttleLevel = rnd.nextDouble();
            breakLevel = rnd.nextDouble();
            turnLevel = (rnd.nextDouble());
        }
        tickCount++;
    }

    @Override
    public double getThrottleLevel() {
        return throttleLevel;
    }

    @Override
    public double getBreakLevel() {
        return breakLevel;
    }

    @Override
    public double getTurnLevel() {
        return turnLevel;
    }
}
