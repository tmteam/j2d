package com.genetics;

import java.util.Random;

public class FatalMutator implements IMutator {
    private final double pOfMutation;

    public FatalMutator(double pOfMutation){

        this.pOfMutation = pOfMutation;
    }
    Random r = new Random();
    @Override
    public void mutate(double[] genom) {
        for (int i = 0; i < genom.length; i++) {
            if(r.nextDouble()>=1-pOfMutation)
                genom[i] = r.nextGaussian()*10;
        }
    }
}
