package com.tutorial.game.GameObjects.Pius;

public interface IPivaBrain {
    void tick(CurrentInput input);
    double getThrottleLevel();
    double getBreakLevel();
    double getTurnLevel();
}

