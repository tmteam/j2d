package com.tutorial.game;

import com.tutorial.game.Cameras.ICamera;
import com.tutorial.game.GameObjects.CircleObject;
import com.tutorial.game.GameObjects.RectangleObject;
import com.tutorial.game.GameObjects.Wall;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Handler  {
    int worldHeight;
    int worldWidth;
    public Handler(ICamera camera, int worldHeight, int worldWidth){
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;
        kinematic= new Kinematic(this .objects,worldHeight, worldWidth);
        display = new Display(new ShiftableCanvas(camera), worldHeight, worldWidth);

        int wallWidth = 20;

        addObject(new Wall(0,0,worldHeight,wallWidth));
        addObject(new Wall(worldWidth-wallWidth,0,worldHeight-1,wallWidth));

        addObject(new Wall(wallWidth,0,wallWidth,worldHeight-2*wallWidth));
        addObject(new Wall(wallWidth,worldWidth-wallWidth,wallWidth,worldHeight-2*wallWidth));

        addObject(new Wall(500,500,500,wallWidth));
    }
    Random r = new Random();
    public void  generateObjects(){

        for(int times = 0; times<3; times++) {
            addObject(new RectangleObject(getRndX(), getRndY(), 40, 60, 1));
            addObject(new RectangleObject(getRndX(), getRndY(), 50, 200, 1));
            addObject(new CircleObject(getRndX(), getRndY(), 40, 2));
            addObject(new CircleObject(getRndX(), getRndY(), 100, 0));
            addObject(new CircleObject(getRndX(), getRndY(), 100, 5));
            addObject(new CircleObject(getRndX(), getRndY(), 40, 0));
            addObject(new CircleObject(getRndX(), getRndY(), 80, 1));

            for (int i = 0; i < 5; i++) {
                addObject(new CircleObject(getRndX(), getRndY(), 80,  1));
                addObject(new CircleObject(getRndX(), getRndY(), 40, 6));
            }
            addObject(new CircleObject(getRndX(), getRndY(), 10, 0));
        }


    }
    double getRndX(){
        return 50+ r.nextInt(worldWidth-300);
    }
    double getRndY(){
        return 50+ r.nextInt(worldHeight-300);
    }
    public void setScreenSize(int width, int height){
        display.setScreenSize(width,height);
    }
    Display display;
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
}
