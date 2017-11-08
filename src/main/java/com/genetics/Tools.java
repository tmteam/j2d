package com.genetics;

public class Tools {

    public static double normalize(double value, double alpha){
        return 1/(1+ Math.exp(-(value*alpha)));
    }

}
