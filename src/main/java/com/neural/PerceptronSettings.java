package com.neural;

public class PerceptronSettings{

    private int inputCount;
    private int outputCount;
    private int[] hiddenCount;
    private double[] weights;
    private double[] offsets;
    public static PerceptronSettings create(PerceptronSettingsInfo info){
        return create(info.inputCount, info.outputCount, info.hiddenCount, info.weights, info.offsets);
    }
    public static PerceptronSettings create(
            int inputCount,
            int outputCount,
            int[] hiddenCount,
            double[] weights,
            double[] offsets) {

        PerceptronSettings settings = new PerceptronSettings();
        settings.inputCount = inputCount;
        settings.outputCount = outputCount;
        settings.hiddenCount = hiddenCount;
        settings.weights = weights;
        settings.offsets = offsets;
        return settings;
    }

    public static PerceptronSettings create(int[] layersConfiguration){
        int neuronsCount = 0;
        int weightsCount = 0;

        for (int layer = 1; layer < layersConfiguration.length; layer++) {
            neuronsCount += layersConfiguration[layer];
            weightsCount += layersConfiguration[layer-1]*layersConfiguration[layer];
        }

        int[] hiddenCounts = new int[layersConfiguration.length-2];
        for (int i = 0; i < hiddenCounts.length; i++) {
            hiddenCounts[i] = layersConfiguration[i+1];
        }

        return create(
                layersConfiguration[0],
                layersConfiguration[layersConfiguration.length-1],
                hiddenCounts,
                new double[weightsCount],
                new double[neuronsCount]);
    }

    public PerceptronSettingsInfo getInfo(){
        PerceptronSettingsInfo ans =new PerceptronSettingsInfo();
        ans.hiddenCount = hiddenCount;
        ans.inputCount = inputCount;
        ans.offsets = offsets;
        ans.weights = weights;
        ans.outputCount = outputCount;
        return ans;
    }

    public int getNeuronsCount() {
        return offsets.length + inputCount;
    }

    public int getInputCount() {
        return inputCount;
    }

    public int getOutputCount() {
        return outputCount;
    }

    public int[] getHiddenCount() {
        return hiddenCount;
    }

    public double[] getWeights() {
        return weights;
    }

    public double[] getOffsets()
    {
        return offsets;
    }

}
