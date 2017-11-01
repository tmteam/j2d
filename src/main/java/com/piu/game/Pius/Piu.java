package com.piu.game.Pius;

import com.piu.engine.GameObject;
import com.piu.engine.ShiftableCanvas;
import com.piu.engine.GameObjects.CircleObject;
import com.piu.game.Levels.GenerationLevel;

import java.awt.*;

public class Piu extends CircleObject {
    private IPivaBrain brain;
    private GenerationLevel handler;

    private double energyLevel = 100000;

    private static final int minimumRadius = 40;

    private IPiuBehaviour currentBehaviour;

    public Piu(IPivaBrain brain, GenerationLevel handler){
        super(0, 0, minimumRadius,0);
        this.brain = brain;
        this.handler = handler;
        currentBehaviour = new PiuAliveBehaviour(this, brain,handler);
        radius = getCurrentRadius();
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
            handler.notifyPiusDeath(this);
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
