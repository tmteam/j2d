package com.genetics;

public class GenomFitness {

    public double[] genom;
    public double fitness;
    public GenomFitness(double[] genom, double fitness){
        this.genom = genom;
        this.fitness = fitness;
    }
    public double[] getGenomCopy(){
        double[] copy = new double[genom.length];
        System.arraycopy(genom,0,copy,0,genom.length);
        return copy;
    }
}
