package com.piu.engine.GameObjects;

import com.piu.engine.ShiftableCanvas;

import java.awt.*;

public class Wall extends RectangleObject {

    public Wall(double x, double y, int height, int width) {
        super(x, y, height, width, 0);
    }

    public double getVelX() { return 0; }
    public double getVelY() { return 0; }
    public void setVelX(double vx) { }
    public void setVelY(double vy) {  }

    public void setX(double x){}
    public void setY(double y){}

    @Override
    public Color getMapColor() {
        return Color.magenta;
    }

    public void  render(ShiftableCanvas canvas){
        canvas.setStroke(2);
        canvas.setColor(new Color(25,25,25));
        canvas.fillRect(0,0,width,height);
        canvas.setColor(Color.magenta);
        canvas.drawRect(0,0,width,height);
    }

    public double getMass(){return 2000000000;}
}
