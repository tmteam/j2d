package com.neural;

public class Perceptron {
    private PerceptronSettings settings;
    private neuron[][] net;

    public Perceptron(PerceptronSettings settings){
        this.settings = settings;
        int[] hiddens = settings.getHiddenCount();
        double[] offsets = settings.getOffsets();
        net = new neuron[1+hiddens.length][];
        int neuronCount = 0;
        int layer = 0;
        for (layer = 0; layer < hiddens.length; layer++) {
            int layerSize = hiddens[layer];
            net[layer] = new neuron[layerSize];
            for (int n = 0; n < layerSize; n++) {
                net[layer][n] = new neuron(offsets[neuronCount]);
                neuronCount++;
            }
        }

        int layerSize = settings.getOutputCount();
        net[layer] = new neuron[layerSize];
        for (int n = 0; n < layerSize; n++) {
            net[layer][n] = new neuron(offsets[neuronCount]);
            neuronCount++;
        }
    }

    public double[] calc(double[] inputs) {

        double[] layerOutput = inputs;
        int weightNum = 0;

        for (int nextLayer = 0; nextLayer < net.length; nextLayer++) {

            for (int thisLayerNeuron = 0; thisLayerNeuron < layerOutput.length; thisLayerNeuron++) {
                for (int nextLayerNeuron = 0; nextLayerNeuron < net[nextLayer].length; nextLayerNeuron++) {
                    net[nextLayer][nextLayerNeuron].append(
                            layerOutput[thisLayerNeuron] * settings.getWeights()[weightNum]);
                    weightNum++;
                }
            }

            layerOutput = new double[net[nextLayer].length];
            for (int nextLayerNeuron = 0; nextLayerNeuron < net[nextLayer].length; nextLayerNeuron++) {
                layerOutput[nextLayerNeuron] = net[nextLayer][nextLayerNeuron].calc();
            }
        }
        return  layerOutput;
    }
}

