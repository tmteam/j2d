package com.tutorial.game;

import com.tutorial.game.Cameras.ICamera;

import java.awt.*;

public class ShiftableCanvas {

    private float xOffset =0;
    private float yOffset = 0;
    private float scale = 1;
    private ICamera camera;

    public ShiftableCanvas(ICamera camera){
        this.camera = camera;
    }

    boolean isObjectInCamera = false;
    double objectX;
    double objectY;
    Graphics currentGraphics;
    public void setCurrentGraphics(Graphics g){
        isObjectInCamera = true;
        currentGraphics = g;
        objectX = 0;
        objectY = 0;
        xOffset = camera.getxOffset();
        yOffset = camera.getyOffset();
        scale = camera.getScale();
        objectX = (xOffset)*scale;
        objectY = (yOffset)*scale;
    }
    public void setPaintingFor(GameObject object){
        isObjectInCamera = true;
        objectX = (object.getX() + xOffset)*scale;
        objectY = (object.getY()+ yOffset)*scale;
    }
    public void setStroke(double width){
        ((Graphics2D)currentGraphics).setStroke ( new BasicStroke((float)(width*scale)));
    }
    public void  drawLine(float x1, float y1, float x2, float y2){
        currentGraphics.drawLine((int)(x1*scale+ objectX),(int)(y1*scale+ objectY),(int)(x2*scale+ objectX),(int)(y2*scale+ objectY));
    }
    public void fillRect(float x, float y, int width, int height){
        currentGraphics.fillRect((int)(x*scale+ objectX), (int)(y*scale+ objectY), (int)(width*scale), (int)(height*scale));
    }
    public void drawRect(float x, float y, int width, int height){
        currentGraphics.drawRect((int)(x*scale+ objectX), (int)(y*scale+ objectY), (int)(width*scale), (int)(height*scale));
    }
    public void fillOval(float x, float y, int width, int height){
        currentGraphics.fillOval ((int)(x*scale+ objectX), (int)(y*scale+ objectY), (int)(width*scale), (int)(height*scale));
    }

    public void drawOval(float x, float y, int width, int height){
        currentGraphics.drawOval ((int)(x*scale+ objectX), (int)(y*scale+ objectY), (int)(width*scale), (int)(height*scale));
    }
    public void  drawArc(float x, float y, int width, int height, int startAngle, int arcAngle){
        currentGraphics.drawArc((int)(x*scale+ objectX),(int)(y*scale+ objectY), width, height, startAngle, arcAngle);
    }
    public void  fillArc(float x, float y, int width, int height, int startAngle, int arcAngle){
        currentGraphics.fillArc((int)(x*scale+ objectX),(int)(y*scale+ objectY), (int)(width*scale), (int)(height*scale), startAngle, arcAngle);
    }
    public void setColor(Color color){
        currentGraphics.setColor(color);
    }


}
