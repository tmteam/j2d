package com.genetics;

import java.util.Random;

public class GausianMutator implements IMutator {
    private final double amplitude;

    public GausianMutator(double amplitude){

        this.amplitude = amplitude;
    }
    Random r = new Random();
    @Override
    public void mutate(double[] genom) {
        for (int i = 0; i < genom.length; i++) {
            genom[i]+= r.nextGaussian()*amplitude;
        }
    }
}
