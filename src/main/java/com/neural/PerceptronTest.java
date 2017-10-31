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
    public void createEmpty2x2_doesNotThrow() {
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
}