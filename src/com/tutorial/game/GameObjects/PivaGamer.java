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
        return Color.green;
    }
    @Override
    public void tick(){
       /* if(energyLevel<=0)
            energyLevel=100000;
*/      if(energyLevel>0) {

            double sina = Math.sin(angle);
            double cosa = Math.cos(angle);

            visionRayResult1 = handler.collideLine(this, (float)getCenterX(),(float)getCenterY(),(float)(angle-0.2), visionLenght);
            visionRayResult2 = handler.collideLine(this, (float)getCenterX(),(float)getCenterY(),(float)(angle), visionLenght);
            visionRayResult3 = handler.collideLine(this, (float)getCenterX(),(float)getCenterY(),(float)(angle+0.2), visionLenght);

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
    @Override
    public void render(ShiftableCanvas g) {

        int diameter = radius*2;
        Color base = Color.cyan;



        if(energyLevel<=0)
            base = Color.red;
        double sina = Math.sin(angle);
        double cosa = Math.cos(angle);
        renderVisionRay(g, visionRayResult1, angle-0.2);
        renderVisionRay(g, visionRayResult2, angle);
        renderVisionRay(g, visionRayResult3, angle+0.2);

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
        int offset = 2;
        g.fillArc(-offset,-offset,diameter+2*offset,diameter+2*offset,ang+ 160, 40);

        //Throttle painting
        int negate = (int)(throttleLevel*255);
        g.setColor(new Color(255, 255-negate, 255-negate));

        int strokeWidth = 4;

        int y = -(int)(sina*(radius-strokeWidth/2));
        int x = (int)(cosa*(radius-strokeWidth/2));

        g.setStroke(strokeWidth);
        g.drawLine(radius+x,radius+y , radius-x,radius-y);
        g.setColor(base);

        g.fillArc(0,0,diameter,diameter,ang-20, 40);

    }
    @Override
    public void afterCollisionWith(GameObject o){
        if(o instanceof Donut){
            handler.removeObject(o);
            energyLevel+=50000;
        }
    }
    private int getCurrentRadius(){
        return minimumRadius+  (int)(Math.sqrt(energyLevel)/3);
    }
}
