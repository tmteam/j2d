package com.tutorial.game.GameObjects.Pius;

import com.tutorial.game.Tools;

public class HumanBrain implements IPivaBrain {
    double turnLevel = 0;
    double throttleLevel = 1;
    double breakLevel = 1;
    boolean leftPushed= false;
    boolean rightPushed= false;

    boolean throttlePushed= false;
    boolean breakPushed = false;

    public void startMoveLeftCommand(){
        leftPushed=true;
    }

    public void startMoveRightCommand(){
        rightPushed = true;
    }

    public void startMoveUpCommand(){
        throttlePushed = true;
    }
    public void startMoveDownCommand(){
        breakPushed = true;
    }

    public void finishMoveLeftCommand(){
        leftPushed = false;
    }

    public void finishMoveRightCommand(){
        rightPushed = false;
    }

    public void finishMoveUpCommand(){
        throttlePushed = false;
    }
    public void finishMoveDownCommand(){
        breakPushed = false;
    }
    @Override
    public void tick(CurrentInput input) {
        if(throttlePushed){
            throttleLevel = Tools.clamp(throttleLevel+0.03,0,1);
        }
        else
            throttleLevel = 0;
        if(breakPushed){
            breakLevel = Tools.clamp(breakLevel+0.05,0,1);
        }
        else
            breakLevel = 0;

        double turnStep = 0.01;
        if(leftPushed){
            turnLevel+=turnStep;
        }
        if(rightPushed){
            turnLevel-= turnStep;
        }
        if(!leftPushed && !rightPushed){
            turnLevel = 0.5;
        }
        turnLevel= Tools.clamp(turnLevel,0,1);

    }

    @Override
    public double getThrottleLevel() {
        return throttleLevel;
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
