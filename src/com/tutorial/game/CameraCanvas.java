package com.tutorial.game;

import java.awt.*;

public class CameraCanvas {

    private float xOffset =0;
    private float yOffset = 0;
    private float scale = 1;

    public CameraCanvas(){
    }


    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
        System.out.println("x y offset: "+xOffset+" : "+ yOffset);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    boolean isObjectInCamera = false;
    double objectX;
    double objectY;
    Graphics currentGraphics;
    public void setCurrentGraphics(Graphics g){
        currentGraphics = g;
    }
    public void setPaintingFor(GameObject object){
        isObjectInCamera = true;
        objectX = (object.getX() + xOffset)*scale;
        objectY = (object.getY()+ yOffset)*scale;
    }
    public void fillRect(float x, float y, int width, int height){
        currentGraphics.fillRect((int)(x+ objectX), (int)(y+ objectY), (int)(width*scale), (int)(height*scale));
    }
    public void drawRect(float x, float y, int width, int height){
        currentGraphics.drawRect((int)(x+ objectX), (int)(y+ objectY), (int)(width*scale), (int)(height*scale));
    }
    public void fillOval(float x, float y, int width, int height){
        currentGraphics.fillOval ((int)(x*scale+ objectX), (int)(y*scale+ objectY), (int)(width*scale), (int)(height*scale));
    }
    public void drawOval(float x, float y, int width, int height){
        currentGraphics.drawOval ((int)(x+ objectX), (int)(y+ objectY), (int)(width*scale), (int)(height*scale));
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
