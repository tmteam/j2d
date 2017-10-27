package com.tutorial.game.GameObjects;

import com.tutorial.game.Collisions.CollisionTools;
import com.tutorial.game.GameObject;
import com.tutorial.game.Handler;
import com.tutorial.game.ShiftableCanvas;

import java.awt.*;

public class CircleObject extends GameObject {

    protected double angle;
    protected double angleVelocity;
    protected int radius;


    public CircleObject(double x, double y, int radius,  int velocity){
        super(x,y);
        this.radius = radius;
        velX = velocity;
        velY = velocity;
        angle =0;
        angleVelocity = 0.00;
    }
    public  double getInertionMoment(){
        return getMass()*radius*radius/2;
    }

    public double getAngularMoment(){
        return getMass()*radius*radius* angleVelocity/2;
    }
    public double getAngleVelocity(){
        return angleVelocity;
    }
    public void  setAngleVelocity(double velocity){
        angleVelocity = velocity;
    }
    public int getRadius(){
        return radius;
    }

    public double getCenterX(){
        return x+radius;
    }

    public double getCenterY(){
        return y+radius;
    }

    @Override
    public void tick() {
        angle+=angleVelocity;
    }

    @Override
    public void render(ShiftableCanvas g) {
        g.setColor(Color.green);
        g.fillOval(0,(int)0,radius*2,radius*2) ;
        g.setColor(Color.black);
        double width = 0.75;
        g.fillOval((int)(radius*(1-width)),(int)(radius*(1-width)),(int)(radius*width*2),(int)(radius*width*2)) ;

        g.setColor(Color.orange);
        int ang = (int)(angle*180/Math.PI);
        int offset =  radius/4;
        g.fillArc(-offset,-offset,radius*2+ offset*2,radius*2+ offset*2, ang, 10);

        g.fillArc(-offset,-offset,radius*2+ offset*2,radius*2+ offset*2, ang+90, 10);
        g.fillArc(-offset,-offset,radius*2+ offset*2,radius*2+ offset*2, ang+180, 10);
        g.fillArc(-offset,-offset,radius*2+ offset*2,radius*2+ offset*2, ang+270, 10);

        g.fillOval(radius-radius/10,(int)radius-radius/10,radius/5,radius/5) ;
    }
    @Override
    public double getMass(){
        return radius*radius* Math.PI;
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,2*radius,2*radius);
    }

    public void Collide( CircleObject target){
        CollisionTools.tryCollide(this, target);
    }
}
