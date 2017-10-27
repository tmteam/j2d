package com.tutorial.game.GameObjects;

import com.tutorial.game.GameObject;
import com.tutorial.game.ShiftableCanvas;
import com.tutorial.game.Tools;

import javax.tools.Tool;
import java.awt.*;

public class PivaGamer extends CircleObject {
    public PivaGamer(double x, double y, int radius){
        super(x, y, radius,0);


    }
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
    public void tick(){
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

        double turnStep = 0.0001;
        if(leftPushed){
            turnLevel+=turnStep;
        }
        if(rightPushed){
            turnLevel-= turnStep;
        }
        if(!leftPushed && !rightPushed){
            turnLevel = 0;
        }
        turnLevel= Tools.clamp(turnLevel,-0.1,0.1);
        angleVelocity+=turnLevel;
        angleVelocity*=0.99;
        double sina = Math.sin(angle);
        double cosa = Math.cos(angle);

        velX+=0.1*throttleLevel*cosa;
        velY-=0.1*throttleLevel*sina;
        velX *=(1 - 0.5*breakLevel);
        velY *=(1 - 0.5*breakLevel);
        super.tick();

    }



    @Override
    public void render(ShiftableCanvas g) {

        int diameter = radius*2;
        Color base = Color.cyan;
        int ang = (int)(angle*180/Math.PI);

        g.setColor(base);

        g.fillOval(0,0,diameter,diameter) ;

        //break painting
        int breakNegate = (int)(breakLevel*255);
        g.setColor(new Color(255, 255-breakNegate, 255-breakNegate));
        g.fillArc(0,0,diameter,diameter,ang+ 200, 20);
        g.fillArc(0,0,diameter,diameter,ang+ 140, 20);


        g.setColor(Color.black);
        g.fillOval(diameter*15/100,diameter*15/100,diameter*70/100,diameter*70/100) ;

        g.fillArc(0,0,diameter,diameter,ang+ 160, 40);

        //Throttle painting
        int negate = (int)(throttleLevel*255);
        g.setColor(new Color(255, 255-negate, 255-negate));

        int strokeWidth = 4;
        double sina = Math.sin(angle);
        double cosa = Math.cos(angle);
        int y = -(int)(sina*(radius-strokeWidth/2));
        int x = (int)(cosa*(radius-strokeWidth/2));

        g.setStroke(new BasicStroke(strokeWidth));
        g.drawLine(radius+x,radius+y , radius-x,radius-y);
        g.setColor(base);




        g.fillArc(0,0,diameter,diameter,ang-20, 40);

    }

}
