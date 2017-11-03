package com.neural;

import java.util.ArrayList;

public class Perceptron {
    private PerceptronSettings settings;
    private neuron[][] net;

    public Perceptron(PerceptronSettings settings){
        this.settings = settings;

        int [] allLayers = settings.getAllLayers();
        double[] offsets = settings.getOffsets();
        net = new neuron[allLayers.length][];

        int neuronCount = 0;

        for (int layer = 0; layer < allLayers.length; layer++) {
            int layerSize = allLayers[layer];
            net[layer] = new neuron[layerSize];
            for (int n = 0; n < layerSize; n++) {
                net[layer][n] = new neuron(offsets[neuronCount]);
                neuronCount++;
            }
        }
    }

    public PerceptronSettings getSettings() {
        return settings;
    }

    public double[] calc(double[] inputs) {

        double[] weights = settings.getWeights();
        double[] layerOutput = inputs;
        int weightNum = 0;
        for (int i = 0; i < net[0].length; i++) {
            net[0][i].append(layerOutput[i]*weights[weightNum]);
            layerOutput[i] = net[0][i].calc();
            weightNum++;
        }

        for (int nextLayer = 1; nextLayer < net.length; nextLayer++) {
            for (int thisLayerNeuron = 0; thisLayerNeuron < layerOutput.length; thisLayerNeuron++) {
                for (int nextLayerNeuron = 0; nextLayerNeuron < net[nextLayer].length; nextLayerNeuron++) {
                    net[nextLayer][nextLayerNeuron].append(
                            layerOutput[thisLayerNeuron] * weights[weightNum]);
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

