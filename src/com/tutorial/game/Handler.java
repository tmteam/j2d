package com.tutorial.game;

import java.awt.*;
import java.util.LinkedList;

public class Handler  {

    public Handler(){
        kinematic= new Kinematic(this .objects);
    }
    Kinematic kinematic;

    LinkedList<GameObject> objects = new LinkedList<>();

    public void tick(){
        for (int i = 0; i< objects.size(); i++) {
            GameObject obj = objects.get(i);
            if(obj!=null)
                obj.tick();
        }
        kinematic.tick();
    }
    public void render(CameraCanvas c){
        for (int i = 0; i< objects.size(); i++) {
            GameObject obj = objects.get(i);
            if(obj!=null) {
                c.setPaintingFor(obj);
                obj.render(c);
            }
        }
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
}
