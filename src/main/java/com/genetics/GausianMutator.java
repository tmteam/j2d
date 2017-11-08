package com.genetics;

import java.util.Random;

public class GausianMutator implements IMutator {
    private final double amplitude;
    private final double pOfMutation;

    public GausianMutator(double amplitude, double pOfMutation){

        this.amplitude = amplitude;
        this.pOfMutation = pOfMutation;
    }
    Random r = new Random();
    @Override
    public void mutate(double[] genom) {
        for (int i = 0; i < genom.length; i++) {
            if(r.nextDouble()>=1-pOfMutation)
                genom[i]+= r.nextGaussian()*amplitude;
        }
    }
}
