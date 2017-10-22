package com.tutorial.game;

import java.awt.*;

public class CircleObject extends GameObject {


    private int radius;
    private Handler handler;


    public CircleObject(double x, double y, int radius,  Handler handler, int velocity){
        super(x,y);
        this.radius = radius;
        this.handler = handler;
        velX = velocity;
        velY = velocity;

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
        //handler.addObject(new Trail( x,y, ID.Trail ,Color.red, 16,16,0.01f, handler));
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
        //g.drawOval((int)x,(int)y,radius*2,radius*2);
       // g.setColor(Color.cyan);
       // g.drawLine((int)x +radius,(int)y, (int)x +radius,(int)y+ 3*radius );
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,2*radius,2*radius);
    }

}
