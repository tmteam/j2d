package com.piu.game.Levels;

import java.util.List;

public class GenerationResults {
    private final List<PiuResult> piuResults;
    private final int generationNum;
    private final int tickResult;

    public GenerationResults(List<PiuResult> piuResults, int generationNum, int tickResult){

        this.piuResults = piuResults;
        this.generationNum = generationNum;
        this.tickResult = tickResult;
    }

    public List<PiuResult> getPiuResults() {
        return piuResults;
    }

    public int getTickResult() {
        return tickResult;
    }

    public int getGenerationNum() {
        return generationNum;
    }
}
