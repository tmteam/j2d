package com.tutorial.game.GameObjects;

import com.tutorial.game.*;
import com.tutorial.game.GameObjects.Piva.IPivaBrain;

import java.awt.*;

public class PivaGamer extends CircleObject {

    private IPivaBrain brain;
    private Handler handler;
    private double breakLevel;
    private double throttleLevel;
    private double turnLevel;

    private double energyLevel =100000;

    private final double throttleCoast = 100;
    private final double breakCoast = 10;
    private final double turnCoast = 100;
    private final double tickCoast = 1;

    private final float visionLenght = 700;

    private static final int minimumRadius = 20;
    private CollideLineResult visionRayResult1 = null;
    private CollideLineResult visionRayResult2 = null;
    private CollideLineResult visionRayResult3 = null;

    public PivaGamer(double x, double y, IPivaBrain brain, Handler handler){
        super(x, y, minimumRadius,0);
        this.brain = brain;
        this.handler = handler;
    }

    @Override
    public Color getMapColor(){
        return Color.cyan;
    }
    @Override
    public void tick(){
       /* if(energyLevel<=0)
            energyLevel=100000;
*/      if(energyLevel>0) {

            double sina = Math.sin(angle);
            double cosa = Math.cos(angle);

            visionRayResult1 = handler.collideLine(this, (float)getCenterX(),(float)getCenterY(),(float)(angle-0.4), visionLenght);
            visionRayResult2 = handler.collideLine(this, (float)getCenterX(),(float)getCenterY(),(float)(angle), visionLenght);
            visionRayResult3 = handler.collideLine(this, (float)getCenterX(),(float)getCenterY(),(float)(angle+0.4), visionLenght);

            brain.tick(null);
            turnLevel = brain.getTurnLevel();
            breakLevel = brain.getBreakLevel();
            throttleLevel = brain.getThrottleLevel();

            double radiusCoast = radius/60d;

            energyLevel -= Math.abs(turnLevel) * turnCoast *radiusCoast;
            energyLevel -= breakLevel * breakCoast *radiusCoast;
            energyLevel -= throttleCoast * throttleLevel *radiusCoast;
            energyLevel -= tickCoast*radiusCoast;

            angleVelocity += brain.getTurnLevel();
            angleVelocity *= 0.99;


            velX += 0.1 * throttleLevel * cosa;
            velY -= 0.1 * throttleLevel * sina;
            velX *= (1 - 0.5 * breakLevel);
            velY *= (1 - 0.5 * breakLevel);

            radius = getCurrentRadius();
        }
        else {
            visionRayResult1 = visionRayResult2 = visionRayResult3 = null;
        }
        super.tick();

    }
    private void renderVisionRay(ShiftableCanvas g, CollideLineResult collide, double rayAngle){

        if(collide!=null){
            double sina = Math.sin(rayAngle);
            double cosa = Math.cos(rayAngle);
            g.setColor(collide.Object.getMapColor());
            g.drawLine(radius,radius, (float)(radius+ collide.Distance*cosa), (float)(radius-collide.Distance*sina));
        }
    }
    int animateFrame = 0;
    @Override
    public void render(ShiftableCanvas g) {
        int diameter = radius*2;
        Color base = Color.cyan;

        if(energyLevel<=0)
            base = Color.red;
        double sina = Math.sin(angle);
        double cosa = Math.cos(angle);

        renderVisionRay(g, visionRayResult1, angle-0.4);
        //renderVisionRay(g, visionRayResult2, angle);
        renderVisionRay(g, visionRayResult3, angle+0.4);


        g.setColor(base);
        g.setStroke(radius/5);
        g.drawOval(0,0,diameter,diameter);
        int ang = (int)(angle*180/Math.PI);

        g.fillArc(radius, radius, radius, ang+ 160, 40);


        if(throttleLevel>0){
            g.setColor(new Color(0xff4500));
            g.fillArc(radius, radius,(int)(radius*(0.2+throttleLevel*1.5)), ang+ 170, 20);
        }
        g.setColor(base);
        g.fillCircle(radius,radius,radius/4);

        animateFrame+=5;
        if(animateFrame>3000)
            animateFrame=0;
        if(animateFrame>0 && animateFrame<500) {

            g.setColor(new Color(Math.min(animateFrame,255),0,0));
            g.fillCircle(radius,radius,radius/6);
        }


        g.setColor(base);
        DrawEye(g, (float)angle-0.4f ,Color.black); //visionRayResult1==null?Color.black:visionRayResult1.Object.getMapColor());
        g.setColor(base);
        DrawEye(g, (float)angle+0.4f,Color.black); //visionRayResult2==null?Color.black:visionRayResult2.Object.getMapColor());
    }

    private void DrawEye(ShiftableCanvas g, float eyeAngle, Color backColor) {
        float cx = radius*(float)(1+ Math.cos(eyeAngle));
        float cy =  radius*(float)(1- Math.sin(eyeAngle));

        float eyeRadius = radius/5;
        g.fillOval(cx-eyeRadius,cy-eyeRadius,(int)eyeRadius*2,(int)eyeRadius*2) ;
        g.setColor(backColor);
        float insideRadius = eyeRadius*0.75f;
        g.fillOval(cx-insideRadius,cy-insideRadius,(int)insideRadius*2,(int)insideRadius*2) ;
    }
    @Override
    public void afterCollisionWith(GameObject o){
        if(o instanceof Donut){
            handler.removeObject(o);
            energyLevel+=5000;
        }
    }

    private int getCurrentRadius(){
        return minimumRadius+  (int)(Math.sqrt(energyLevel)/3);
    }
}
