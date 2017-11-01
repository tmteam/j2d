package com.piu.game.Levels;

import java.util.List;

public class GenerationResults {
    private final List<PiuResult> piuResults;
    private final int tickResult;

    public GenerationResults(List<PiuResult> piuResults, int tickResult){

        this.piuResults = piuResults;
        this.tickResult = tickResult;
    }

    public List<PiuResult> getPiuResults() {
        return piuResults;
    }

    public int getTickResult() {
        return tickResult;
    }
}
