package com.neural;

public class neuron{

    public double offset;

    private double weightedInputSumm = 0;

    public neuron(double offset) {
        this.offset = offset;
    }

    public void append(double weightedSumm){
        weightedInputSumm+=weightedSumm;

    }

    public double calc(){
        double ans = Tools.exp(weightedInputSumm,offset);
        weightedInputSumm = 0;
        return ans;
    }
}
