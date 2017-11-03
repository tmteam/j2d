package com.piu.game.Pius;

import com.piu.engine.CollideLineResult;
import com.piu.engine.GameObject;
import com.piu.engine.ShiftableCanvas;
import com.piu.game.Donut;
import com.piu.game.Levels.GenerationLevel;

import java.awt.*;

public class PiuAliveBehaviour implements IPiuBehaviour {
    private Piu piu;
    private IPivaBrain brain;
    private GenerationLevel handler;
    private double breakLevel;
    private double throttleLevel;
    private double turnLevel;

    private final double throttleCoast = 8;
    private final double breakCoast = 10;
    private final double turnCoast = 200;
    public static final double tickCoast = 30;

    private final float visionLenght = 800;
    private  boolean isAlive = true;
    private CollideLineResult visionRayResult1 = null;
    private CollideLineResult visionRayResult2 = null;

    public PiuAliveBehaviour(Piu piu, IPivaBrain brain, GenerationLevel handler){
        this.piu = piu;
        this.brain = brain;
        this.handler = handler;
    }

    @Override
    public Color getMapColor(){
        return Color.cyan;
    }

    @Override
    public void tick(){

        double velX  = piu.getVelX();
        double velY = piu.getVelY();
        int radius = piu.getRadius();
        double angleVelocity = piu.getAngleVelocity();
        double angle = piu.getAngle();
        double sina = Math.sin(angle);
        double cosa = Math.cos(angle);
        double energyLevel = piu.getEnergyLevel();

        visionRayResult1 = handler.collideLine(piu, piu.getCenterX(), piu.getCenterY(),(float)(angle-0.4), visionLenght);
        visionRayResult2 = handler.collideLine(piu, piu.getCenterX(), piu.getCenterY(),(float)(angle+0.4), visionLenght);

        CurrentInput input = new CurrentInput();
        input.energy = energyLevel;
        input.velX =  velX;
        input.velY = velY;
        input.angleVel = angleVelocity;

        input.sensor1 = new SensorInfo();

        if(visionRayResult1!=null && visionRayResult1.Object!=null)
        {
            input.sensor1.distance = visionRayResult1.Distance;
            input.sensor1.sensorColor = visionRayResult1.Object.getMapColor();
            input.sensor1.dVelX= visionRayResult1.Object.getVelX()-velX;
            input.sensor1.dVelY = visionRayResult1.Object.getVelY()-velY;
        }

        input.sensor2 = new SensorInfo();
        if(visionRayResult2!=null && visionRayResult2.Object!=null)
        {
            input.sensor2.distance = visionRayResult2.Distance;
            input.sensor2.sensorColor = visionRayResult2.Object.getMapColor();
            input.sensor2.dVelX= visionRayResult2.Object.getVelX()-velX;
            input.sensor2.dVelY = visionRayResult2.Object.getVelY()-velY;
        }


        brain.tick(input);
        turnLevel = (brain.getTurnLevel() -0.5)/50d;

        if(Math.abs(angleVelocity+turnLevel)>0.2)
            turnLevel =0;

        breakLevel = brain.getBreakLevel();
        throttleLevel = brain.getThrottleLevel();

        double radiusCoast = radius/60d;

        energyLevel -= Math.abs(turnLevel) * turnCoast *radiusCoast;
        energyLevel -= breakLevel * breakCoast *radiusCoast;
        energyLevel -= throttleCoast * throttleLevel *radiusCoast;
        energyLevel -= tickCoast*radiusCoast;

        piu.setEnergyLevel(energyLevel);

        angleVelocity += turnLevel;

        velX += 0.1 * throttleLevel * cosa;
        velY -= 0.1 * throttleLevel * sina;
        velX *= (1 - 0.5 * breakLevel);
        velY *= (1 - 0.5 * breakLevel);

        piu.setVelX(velX);
        piu.setVelY(velY);
        piu.setAngleVelocity(angleVelocity);

    }
    private void renderVisionRay(ShiftableCanvas g, CollideLineResult collide, int radius, double rayAngle){

        if(collide!=null){
            double sina = Math.sin(rayAngle);
            double cosa = Math.cos(rayAngle);
            g.setColor(collide.Object.getMapColor());
            g.drawLine(radius,radius, (float)(radius+ collide.Distance*cosa), (float)(radius-collide.Distance*sina));
        }
    }
    int animateFrame = 0;

    @Override
    public void render(ShiftableCanvas g, int radius, double angle) {
        int diameter = radius * 2;
        Color base = Color.cyan;

        if (!isAlive)
            base = Color.blue;

        g.setStroke(1);

        //  renderVisionRay(g, visionRayResult1, angle - 0.4);
        //    renderVisionRay(g, visionRayResult2, angle + 0.4);

        g.setColor(base);
        g.setStroke(radius / 5);
        g.drawOval(0, 0, diameter, diameter);
        int ang = (int) (angle * 180 / Math.PI);

        g.fillArc(radius, radius, radius, ang + 160, 40);


        if (throttleLevel > 0) {
            g.setColor(new Color(0xff4500));
            g.fillArc(radius, radius, (int) (radius * (0.2 + throttleLevel * 1.5)), ang + 170, 20);
        }
        g.setColor(base);
        g.fillCircle(radius, radius, radius / 4);

        animateFrame += 5;
        if (animateFrame > 3000)
            animateFrame = 0;
        if (animateFrame > 0 && animateFrame < 500) {

            g.setColor(new Color(Math.min(animateFrame, 255), 0, 0));
            g.fillCircle(radius, radius, radius / 6);
        }

        g.setColor(base);
        DrawEye(g, (float) angle - 0.4f, Color.black,radius); //visionRayResult1==null?Color.black:visionRayResult1.Object.getMapColor());
        g.setColor(base);
        DrawEye(g, (float) angle + 0.4f, Color.black,radius); //visionRayResult2==null?Color.black:visionRayResult2.Object.getMapColor());
    }

    private void DrawEye(ShiftableCanvas g, float eyeAngle, Color backColor, int radius) {
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
            handler.donutEaten((Donut)o);
            piu.setEnergyLevel(piu.getEnergyLevel()+15000);
        }
    }
}
