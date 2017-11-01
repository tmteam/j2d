package com.piu.game.Pius;

import com.neural.Perceptron;
import com.neural.PerceptronSettings;
import com.piu.game.GenerationHandler;

public class PiuFactory {

    private final NeuralBrain brain;
    public static PiuFactory createRandom( int[] layersConfig){
        return new PiuFactory(new NeuralBrain(new Perceptron(PerceptronSettings.createRandom(layersConfig))));
    }
    public PiuFactory(NeuralBrain brain){

        this.brain = brain;
    }
    public Piu createFor(GenerationHandler handler){
        return new Piu(brain, handler);
    }
}
