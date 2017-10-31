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

        int neuronsCount = layersConfiguration[0];
        int weightsCount = layersConfiguration[0];


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

    public static PerceptronSettings create(int[] layersConfiguration, double[] genom){
        PerceptronSettings ans = create(layersConfiguration);
        int [] allLayers = new int[layersConfiguration.length+1];
        allLayers[0] = 1;
        System.arraycopy(layersConfiguration,0,allLayers,1,layersConfiguration.length);

        int weightPosition = 0;
        int offsetPosition = 0;
        int genomPosition = 0;

        for (int i = 1; i < allLayers.length; i++) {
            int weightsCount = allLayers[i-1]* allLayers[i];

            System.arraycopy( genom,genomPosition,ans.getWeights() , weightPosition,weightsCount);
            genomPosition+= weightsCount;
            weightPosition+=weightsCount;

            //NeuronOffsets
            int offsetsCount = allLayers[i];
            System.arraycopy( genom,genomPosition,ans.getOffsets(), offsetPosition,offsetsCount);
            genomPosition+= offsetsCount;
            offsetPosition += offsetsCount;
        }
        return ans;
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
    public int[] getAllLayers(){
        int [] allLayers = new int[hiddenCount.length+2];
        allLayers[0] = getInputCount();
        for (int i = 0; i < hiddenCount.length; i++) {
            allLayers[i+1] = hiddenCount[i];
        }
        allLayers[allLayers.length-1] = outputCount;
        return allLayers;

    }
    public int getNeuronsCount() {
        return offsets.length;
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

    public double[] toGenom(){
        //InputWeights
        //foreach layer
        //    offsets
        //    allOutputWeights (1 by 1)

        double[] genom = new double[offsets.length+weights.length];

        int weightPosition = 0;
        int offsetPosition = 0;
        int genomPosition = 0;

        int [] allLayers = new int[hiddenCount.length+3];
        allLayers[0] = 1; //needToWrite input weights
        allLayers[1] = inputCount;
        System.arraycopy(hiddenCount, 0,allLayers,2, hiddenCount.length);
        allLayers[allLayers.length-1] = outputCount;

        for (int i = 1; i < allLayers.length; i++) {
            //    allLayerInputWeights
            int weightsCount = allLayers[i-1]* allLayers[i];
            System.arraycopy( weights,weightPosition,genom, genomPosition,weightsCount);
            genomPosition+= weightsCount;
            weightPosition+=weightsCount;

            //NeuronOffsets
            int offsetsCount = allLayers[i];
            System.arraycopy( offsets,offsetPosition,genom, genomPosition,offsetsCount);
            genomPosition+= offsetsCount;
            offsetPosition += offsetsCount;
        }
        return genom;
    }

    public boolean isSimilarTo(PerceptronSettings otherSettings){
        if(otherSettings.inputCount!= this.inputCount)
            return false;
        if(otherSettings.outputCount != this.outputCount)
            return false;

        if(otherSettings.hiddenCount.length!= this.hiddenCount.length)
            return false;
        for (int i = 0; i < hiddenCount.length; i++) {
            if(otherSettings.hiddenCount[i]!= this.hiddenCount[i])
                return false;
        }

        for (int i = 0; i < weights.length; i++) {
            if(otherSettings.weights[i]!= this.weights[i])
                return false;
        }
        for (int i = 0; i < offsets.length; i++) {
            if(otherSettings.offsets[i]!= this.offsets[i])
                return false;
        }
        return true;
    }
}
