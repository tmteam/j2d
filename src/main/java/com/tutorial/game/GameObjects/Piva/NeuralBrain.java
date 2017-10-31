package com.tutorial.game.GameObjects.Piva;

import com.neural.Perceptron;

import java.util.ArrayList;

public class NeuralBrain implements IPivaBrain {

    private Perceptron perceptron;
    double thtrottleLevel=0;
    double breakLevel = 0;
    double turnLevel = 0;

    public NeuralBrain(com.neural.Perceptron perceptron){

        this.perceptron = perceptron;
    }
    @Override
    public void tick(CurrentInput input) {
        //angleVel
        //velX
        //velY
        //Energy
        //Throttle
        //Break
        //Turn

        //Sensor1

        //input.sensor1.distance;
        //input.sensor1.sensorColor.getRed()
        //input.sensor1.sensorColor.getGreen()
        //input.sensor1.sensorColor.getBlue()
        //input.dvx
        //input.dvy

        double[] inputs = new double[19];

        inputs[0]=input.angleVel;
        inputs[1]=input.angleVel;
        inputs[2]=input.velX;
        inputs[3]=input.velY;
        inputs[4]=input.energy;
        inputs[5]=thtrottleLevel;
        inputs[6]=turnLevel;
        inputs[7]=breakLevel;
        fillSensor(inputs,8, input.sensor1);
        fillSensor(inputs,13, input.sensor2);

        double[] outputs = perceptron.calc(inputs);

        thtrottleLevel = outputs[0];
        breakLevel = outputs[1];
        turnLevel = outputs[2];
    }


    private void fillSensor(double[] inputs, int offset, SensorInfo sensor) {
        inputs[offset+0]=  sensor.distance;
        inputs[offset+1]= sensor.sensorColor.getRed()/255d;
        inputs[offset+2]= sensor.sensorColor.getGreen()/255d;
        inputs[offset+3]= sensor.sensorColor.getBlue()/255d;
        inputs[offset+4]= sensor.dVelX;
        inputs[offset+5]= sensor.dVelY;
    }

    @Override
    public double getThrottleLevel() {
        return thtrottleLevel;
    }

    @Override
    public double getBreakLevel() {
        return breakLevel;
    }

    @Override
    public double getTurnLevel() {
        return turnLevel;
    }
}
