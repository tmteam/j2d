package com.neural;

public class Tools {
    public static double exp(double value, double offset){
        return 1/(1+ Math.exp(-((value-offset))));
    }
}
