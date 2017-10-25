package com.tutorial.game.GameObjects;

import com.tutorial.game.GameObject;
import com.tutorial.game.Handler;
import com.tutorial.game.ShiftableCanvas;

import java.awt.*;

public class RectangleObject extends GameObject {

    protected int height;
    protected int width;

    public RectangleObject(double x, double y, int height, int width,  int velocity){
        super(x,y);
        this.height = height;
        this.width = width;
        velX = velocity;
        velY = velocity;
    }

    @Override
    public void tick() {

        //handler.addObject(new Trail( x,y, ID.Trail ,Color.red, 16,16,0.01f, handler));
    }

    @Override
    public void render(ShiftableCanvas g) {
        g.setColor(new Color(0xFF4500));
        g.fillRect(0,0,width,height) ;
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
