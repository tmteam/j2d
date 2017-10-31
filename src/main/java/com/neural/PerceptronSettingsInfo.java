package com.neural;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PerceptronSettingsInfo {
    public int inputCount;
    public int outputCount;
    public int[] hiddenCount;
    public double[] weights;
    public double[] offsets;

    public PerceptronSettings ToSettings(){
        return PerceptronSettings.create(this);
    }
    /*
    public static void WriteToJson(PerceptronSettingsInfo info){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        System.out.println(gson.toJson(info));
    }*/
}
