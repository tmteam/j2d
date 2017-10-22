package com.tutorial.game;

import java.awt.*;

public abstract class GameObject {

    protected double x,y;
    protected double velX, velY;
    public GameObject(double x, double y){
        this.x = x;
        this.y = y;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void  setX(double x){
        this.x = x;
    }
    public void  setY(double y){
        this.y = y;
    }
    public double getX(){return x;}
    public double getY(){return y;}


    public void  setVelX(double velX){
        this.velX = velX;
    }
    public void  setVelY(double velY){
        this.velY = velY;
    }
    public double getVelX(){return velX;}
    public double getVelY(){return velY;}
}
