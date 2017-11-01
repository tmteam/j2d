package com.piu.engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SurfaceWorldHandler implements ISurfaceWorldHandler {

    private final int worldwidth;
    private final int worldheight;
    Display display;
    Kinematic kinematic;

    ArrayList<GameObject> objects = new ArrayList<>();
    public SurfaceWorldHandler(Display display, int worldwidth, int worldheight){
        this.worldwidth = worldwidth;
        this.worldheight = worldheight;

        Kinematic kinematic= new Kinematic(this.objects, worldwidth, worldheight);
        this.kinematic= kinematic;
        this.display = display;
    }

    @Override
    public void setScreenSize(int width, int height){
        display.setScreenSize(width,height);
    }



    @Override
    public void tick(){
        for (int i = 0; i< objects.size(); i++) {
            GameObject obj = objects.get(i);
            if(obj!=null)
                obj.tick();
        }
        kinematic.tick();

    }
    @Override
    public void render(Graphics g){
        display.Render(g,objects);
    }
    int objectCount = 0;

    public int getObjectCount(){
        return objectCount;
    }

    public void  addObject(GameObject object){
        objectCount++;
        this.objects.add(object);

    }
    public void  removeObject(GameObject object){
        objectCount--;
        this.objects.remove(object);
    }

    public boolean  addObjectAtRandomFreePlace(GameObject object){
        for (int i = 0; i < 1000; i++) {

            if(!kinematic.areIntersected(object.getBounds())) {
                addObject(object);
                return true;
            }
            object.setX(getRndX());
            object.setY(getRndY());
        }
        return false;
    }
    Random r = new Random();

    double getRndX(){
        return r.nextInt(worldwidth);
    }
    double getRndY(){
        return r.nextInt(worldheight);
    }

    public CollideLineResult collideLine(GameObject except, float x1, float y1, float angle, float lenght){
        return kinematic.collideLine(except,x1,y1,angle,lenght);
    }
}
