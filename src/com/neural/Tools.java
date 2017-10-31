package com.neural;

public class Tools {
    public static double exp(double value, double alpha, double offset){
        return 1/(1+ Math.exp(-(alpha*(value-offset))));
    }
}
