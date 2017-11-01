package com.tutorial.game.GameObjects.Pius;

import com.tutorial.game.CollideLineResult;
import com.tutorial.game.GameObject;
import com.tutorial.game.GameObjects.CircleObject;
import com.tutorial.game.GameObjects.Donut;
import com.tutorial.game.GameObjects.Pius.CurrentInput;
import com.tutorial.game.GameObjects.Pius.IPivaBrain;
import com.tutorial.game.GameObjects.Pius.SensorInfo;
import com.tutorial.game.Handler;
import com.tutorial.game.ShiftableCanvas;

import java.awt.*;

public class Piu extends CircleObject {
    private IPivaBrain brain;
    private Handler handler;

    private double energyLevel =100000;


    private static final int minimumRadius = 40;

    private IPiuBehaviour currentBehaviour;

    public Piu(double x, double y, IPivaBrain brain, Handler handler){
        super(x, y, minimumRadius,0);
        this.brain = brain;
        this.handler = handler;
        currentBehaviour = new PiuAliveBehaviour(this, brain,handler);
    }
    public void  setEnergyLevel(double value){
        energyLevel =  value;
    }
    public boolean isAlive(){
        return currentBehaviour instanceof PiuAliveBehaviour;
    }
    public double getEnergyLevel() {
        return energyLevel;
    }

    @Override
    public Color getMapColor(){
        return currentBehaviour.getMapColor();
    }
    @Override
    public void tick(){
        currentBehaviour.tick();
        if(energyLevel<=0 && isAlive()){
            //It is death
            currentBehaviour = new PiuDeadBehaviour();
        }
        angleVelocity *= 0.99;
        radius = getCurrentRadius();
        super.tick();

    }
    @Override
    public void render(ShiftableCanvas g) {
        currentBehaviour.render(g, radius,angle);
    }


    @Override
    public void afterCollisionWith(GameObject o){
        currentBehaviour.afterCollisionWith(o);
    }

    private int getCurrentRadius(){

        return minimumRadius+  (int)(Math.sqrt(energyLevel)/5);
    }
}
