package com.tutorial.game;

import java.awt.*;

public class RectangleObject extends GameObject {

    private int height;
    private int width;
    private Handler handler;

    public RectangleObject(double x, double y, int height, int width,  Handler handler, int velocity){
        super(x,y);
        this.height = height;
        this.width = width;
        this.handler = handler;
        velX = velocity;
        velY = velocity;
    }

    @Override
    public void tick() {

        //handler.addObject(new Trail( x,y, ID.Trail ,Color.red, 16,16,0.01f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.drawRect((int)x,(int)y,width,height);
    }
    @Override
    public double getMass(){
        return  width*height;
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,width,height);
    }
}
