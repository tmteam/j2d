package com.genetics;

import java.util.Random;

public class SoftGausianMutator implements IMutator {
    private final double pOfMutation;
    private final double amplitude;
    public SoftGausianMutator(double pOfMutation, double amplitude){

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
