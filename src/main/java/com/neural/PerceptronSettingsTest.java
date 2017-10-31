package com.neural;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PerceptronSettingsTest {
    @Test
    public void createWithZeroSizeDoesNotThrow()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{0,0});
    }
    @Test
    public void createWithZeroSize_WeightsCountEqualsZero()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{0,0});
        assertEquals(0,settings.getWeights().length);
    }
    @Test
    public void createWithZeroSize_NeuronsCountEqualsZero()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{0,0});
        assertEquals(0,settings.getNeuronsCount());
    }
    @Test
    public void create2x2_NeuronsCountEquals4()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,2});
        assertEquals(4,settings.getNeuronsCount());
    }
    @Test
    public void create2x2_WeightsCountEquals4()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,2});
        assertEquals(6,settings.getWeights().length);
    }
    @Test
    public void create2x3x4x3_WeightsCountEquals32()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,3,4,3});
        assertEquals(32,settings.getWeights().length);
    }
    @Test
    public void create2x3x4x3_OffsetsCountEquals12()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,3,4,3});
        assertEquals(12,settings.getOffsets().length);
    }
    @Test
    public void create2x3x4x3_outputCountEquals3()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,3,4,3});
        assertEquals(3,settings.getOutputCount());
    }
    @Test
    public void create2x3x4x3_inputCountEquals2()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,3,4,3});
        assertEquals(2,settings.getInputCount());
    }
    @Test
    public void  create2x2_toGenomNotThrows(){
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,2});
        settings.toGenom();
    }

    @Test
    public void  create2x3x4x5_toGenomNotThrows(){
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,3,4,5});
        settings.toGenom();
    }
    @Test
    public void  randomGenomRecreates_notThrows(){
        int[] configuration = new int[]{2,3,4,5};
        double[] genom = createRandomGenomFor(configuration);
        PerceptronSettings.create(configuration,genom);
    }

    @Test
    public void  randomGenomRecreatesWithSameGenom(){
        int[] configuration = new int[]{2,3,4,5};
        double[] genom = createRandomGenomFor(configuration);
        PerceptronSettings randomSettings = PerceptronSettings.create(configuration,genom);
        assertArrayEquals(genom, randomSettings.toGenom(), 0.01);
    }

    private double[] createRandomGenomFor(int[] configuration) {
        PerceptronSettings settings= PerceptronSettings.create(configuration);
        double[] genom =  settings.toGenom();
        Random rnd = new Random();
        for (int i = 0; i < genom.length; i++) {
            genom[i] = rnd.nextDouble();
        }
        return genom;
    }
}