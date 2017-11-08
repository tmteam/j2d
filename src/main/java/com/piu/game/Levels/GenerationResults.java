package com.piu.game.Levels;

import java.util.List;

public class GenerationResults {
    private final List<PiuResult> piuResults;
    private final int generationNum;
    private final int tickResult;
    private final int donnutsEaten;
    private final int donnutsLeft;

    public GenerationResults(List<PiuResult> piuResults, int generationNum, int tickResult, int donnutsEaten, int donnutsLeft){

        this.piuResults = piuResults;
        this.generationNum = generationNum;
        this.tickResult = tickResult;
        this.donnutsEaten = donnutsEaten;
        this.donnutsLeft = donnutsLeft;
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

    public int getDonnutsEaten() {
        return donnutsEaten;
    }

    public int getDonnutsLeft() {
        return donnutsLeft;
    }
}
