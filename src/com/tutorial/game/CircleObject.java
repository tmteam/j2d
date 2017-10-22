package com.tutorial.game;

import java.awt.*;

public class CircleObject extends GameObject {

    private double angle;
    private double angleVelocity;
    private int radius;
    private Handler handler;


    public CircleObject(double x, double y, int radius,  Handler handler, int velocity){
        super(x,y);
        this.radius = radius;
        this.handler = handler;
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
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillOval((int)x,(int)y,radius*2,radius*2) ;
        g.setColor(Color.black);
        double centerX =  x+radius;
        double centerY = y+radius;
        double width = 0.75;
        g.fillOval((int)(centerX- radius*width),(int)(centerY-radius*width),(int)(radius*width*2),(int)(radius*width*2)) ;
        g.setColor(Color.cyan);
        int ang = (int)(angle*180/Math.PI);
        g.fillArc((int)x,(int)y,radius*2,radius*2, ang, 10);
        g.fillOval((int)centerX-radius/10,(int)centerY-radius/10,radius/5,radius/5) ;
    }
    @Override
    public double getMass(){
        return radius*radius* Math.PI;
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,2*radius,2*radius);
    }

}
