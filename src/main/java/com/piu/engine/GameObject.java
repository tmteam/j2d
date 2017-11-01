package com.piu.engine;

import com.piu.engine.Collisions.ColideSide;
import com.piu.engine.Collisions.CollideCalculationResult;

import java.awt.*;

public abstract class GameObject {

    protected double x,y;
    protected double velX, velY;
    public GameObject(double x, double y){
        this.x = x;
        this.y = y;
    }
    public abstract void tick();
    public abstract void render(ShiftableCanvas g);
    public abstract Rectangle getBounds();
    public  double getMass(){return 1;}
    public void  setX(double x){
        this.x = x;
    }
    public void  setY(double y){
        this.y = y;
    }
    public double getX(){return x;}
    public double getY(){return y;}

    public Point getFirstIntersectionWith(float x1, float y1, float x2, float y2){
        Rectangle bounds = getBounds();
        CollideCalculationResult collideCalc = new CollideCalculationResult(x1,y1,x2,y2);
        collideCalc.checkSide(ColideSide.left,x,y,x,y+bounds.height);
        collideCalc.checkSide(ColideSide.right,bounds.getMaxX(),y,bounds.getMaxX(),bounds.getMaxY());
        collideCalc.checkSide(ColideSide.top,x,y,bounds.getMaxX(),y);
        collideCalc.checkSide(ColideSide.bottom,x,bounds.getMaxY(),bounds.getMaxX(),bounds.getMaxY());

        return collideCalc.collidePoint;
    }

    public boolean tryCollideWith(GameObject o){
        return false;
    }
    public void afterCollisionWith(GameObject o){

    }

    public Color getMapColor(){ return Color.white; }

    public void  setVelX(double velX){
        this.velX = velX;
    }
    public void  setVelY(double velY){
        this.velY = velY;
    }
    public double getVelX(){return velX;}
    public double getVelY(){return velY;}
}
