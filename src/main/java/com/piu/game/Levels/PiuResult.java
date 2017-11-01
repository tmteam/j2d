package com.piu.game.Levels;

import com.piu.game.Pius.Piu;

public class PiuResult{
    public PiuResult(Piu piu, double fitness){
        this.piu = piu;
        this.fitness = fitness;
    }
    public Piu piu;
    public double fitness;
}
