package com.tutorial.game.GameObjects.Piva;

public interface IPivaBrain {
    void tick(CurrentInput input);
    double getThrottleLevel();
    double getBreakLevel();
    double getTurnLevel();
}

