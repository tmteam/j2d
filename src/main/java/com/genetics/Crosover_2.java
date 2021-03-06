package com.genetics;

import java.util.Random;

public class Crosover_2 {

    private double[] child1;
    private double[] child2;
    Random r = new Random();
    public void  calc(double[] genom1, double[] genom2){
        int crosover1 =  r.nextInt(genom1.length-1);
        int crosover2 =  r.nextInt(genom1.length-1);

        child1 = new double[genom1.length];
        child2 = new double[genom1.length];
        boolean read1 = true;

        for (int i = 0; i < genom1.length; i++) {
            if(read1)
            {
                child1[i] = genom1[i];
                child2[i] = genom2[i];
            }
            else {
                child1[i] = genom2[i];
                child2[i] = genom1[i];
            }
            if(i == crosover1)
                read1=false;
            if(i==crosover2)
                read1 = true;

        }
    }

    public double[] getChild1() {
        return child1;
    }

    public double[] getChild2() {
        return child2;
    }
}
