package com.neural;

import org.junit.Test;

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
        assertEquals(4,settings.getWeights().length);
    }
    @Test
    public void create2x3x4x3_WeightsCountEquals30()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,3,4,3});
        assertEquals(30,settings.getWeights().length);
    }
    @Test
    public void create2x3x4x3_OffsetsCountEquals10()  {
        PerceptronSettings settings= PerceptronSettings.create(new int[]{2,3,4,3});
        assertEquals(30,settings.getWeights().length);
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


}