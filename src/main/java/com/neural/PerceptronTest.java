package com.neural;

import org.junit.Test;

import static org.junit.Assert.*;

public class PerceptronTest {

    @Test
    public void createEmptyWithHidden_doesNotThrow() {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,3,4,5,2});
        Perceptron perceptron = new Perceptron(settings);
    }
    @Test
    public void createEmptyWithHidden_calcReturnsHalfAndHalf() {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{3,3,4,5,3});
        Perceptron perceptron = new Perceptron(settings);
        double[] ans = perceptron.calc(new double[]{0,0,3});
        assertArrayEquals( new double[]{0.5,0.5,0.5},ans,0.01);
    }
    @Test
    public void createEmpty2x2_CalcdoesNotThrow() {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,2});
        Perceptron perceptron = new Perceptron(settings);
        double[] ans = perceptron.calc(new double[]{0,0});
        assertArrayEquals( new double[]{0.5,0.5},ans,0.01);
    }
    @Test
    public void createEmpty2x2_calcReturnsHalfAndHalf() {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,2});
        Perceptron perceptron = new Perceptron(settings);
        double[] ans = perceptron.calc(new double[]{0,0});
        assertArrayEquals( new double[]{0.5,0.5},ans,0.01);
    }

    @Test
    public void  create1x1x1_weightsAre1_calcReturnsCorrectValue(){
        PerceptronSettings settings= PerceptronSettings.create(new int[]{1,1});
        for (int i = 0; i <settings.getWeights().length ; i++) {
            settings.getWeights()[i] = 1;
        }

        Perceptron perceptron = new Perceptron(settings);
        double[] ans =  perceptron.calc(new double[]{1});

        double expected = 1;
        expected = Tools.exp(expected, 0);
        expected = Tools.exp(expected, 0);
        expected = Tools.exp(expected, 0);
        assertArrayEquals(new double[]{expected}, ans,0.05);
    }
    @Test
    public void  create1x1x1_weightsAre2_calcReturnsCorrectValue(){
        PerceptronSettings settings= PerceptronSettings.create(new int[]{1,1});
        for (int i = 0; i <settings.getWeights().length ; i++) {
            settings.getWeights()[i] = 2;
        }

        Perceptron perceptron = new Perceptron(settings);
        double[] ans =  perceptron.calc(new double[]{1});

        double expected = 1;
        expected = Tools.exp(2*expected, 0);
        expected = Tools.exp(2*expected, 0);
        expected = Tools.exp(2*expected, 0);
        assertArrayEquals(new double[]{expected}, ans,0.01);
    }

    @Test
    public void  create1x1x1_weightsAndOffsetsAre1_calcReturnsCorrectValue(){
        PerceptronSettings settings= PerceptronSettings.create(new int[]{1,1});
        for (int i = 0; i <settings.getWeights().length ; i++) {
            settings.getWeights()[i] = 1;
        }
        for (int i = 0; i < settings.getOffsets().length; i++) {
            settings.getOffsets()[i] = 1;
        }
        Perceptron perceptron = new Perceptron(settings);
        double[] ans =  perceptron.calc(new double[]{1});

        double expected = 1;
        expected = Tools.exp(expected, 1);
        expected = Tools.exp(expected, 1);
        expected = Tools.exp(expected, 1);
        assertArrayEquals(new double[]{expected}, ans,0.05);
    }
}