package com.piu.engine.GameObjects;

import com.piu.engine.Collisions.*;
import com.piu.engine.ShiftableCanvas;
import com.piu.engine.GameObject;

import java.awt.*;

public class RectangleObject extends GameObject {

    protected int height;
    protected int width;
    protected SmartRectCollisionHandler rectCollider = new SmartRectCollisionHandler();
    private CircleToRectCollisionHandler circleToRectCollisionHandler = new CircleToRectCollisionHandler();
    public RectangleObject(double x, double y, int height, int width,  int velocity){
        super(x,y);
        this.height = height;
        this.width = width;
        velX = velocity;
        velY = velocity;
    }

    @Override
    public Point getFirstIntersectionWith(float x1, float y1, float x2, float y2) {
        CollideCalculationResult collideCalc = new CollideCalculationResult(x1,y1,x2,y2);
        collideCalc.checkSide(ColideSide.left,x,y,x,y+height);
        collideCalc.checkSide(ColideSide.right,x+width,y,x+width,y+height);
        collideCalc.checkSide(ColideSide.top,x,y,x+width,y);
        collideCalc.checkSide(ColideSide.bottom,x,y+height,x+width,y+height);
        return collideCalc.collidePoint;
    }

    @Override
    public boolean tryCollideWith(GameObject o){
        if(o instanceof RectangleObject){
            CollisionTools.CollideRects(this,o);
            return true;
        }
        if(o instanceof  CircleObject){
            CollisionTools.CollideCircleAndRectangle((CircleObject)o, this);
            return true;
        }
        return false;
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
