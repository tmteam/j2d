package com.genetics;

import java.lang.reflect.Array;
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
        int len = r.nextInt((genom.length-2)/8+1);

        int start1 = r.nextInt(genom.length-len -1);
        int start2 = r.nextInt(genom.length-len -1);

        double[] buff = new double[len];

        System.arraycopy(genom, start1,buff ,0,len);
        System.arraycopy(genom, start2,genom,start1,len);
        System.arraycopy(buff,0, genom, start2,len);
    }
}
