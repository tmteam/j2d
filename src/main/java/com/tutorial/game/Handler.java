package com.tutorial.game;

import com.neural.Perceptron;
import com.neural.PerceptronSettings;
import com.tutorial.game.Cameras.ICamera;
import com.tutorial.game.GameObjects.*;
import com.tutorial.game.GameObjects.Pius.NeuralBrain;
import com.tutorial.game.GameObjects.Pius.Piu;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Handler  {
    int worldHeight;
    int worldWidth;
    public Handler(ICamera camera, int worldHeight, int worldWidth){
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;
        kinematic= new Kinematic(this.objects,worldHeight, worldWidth);
        display = new Display(new ShiftableCanvas(camera), worldHeight, worldWidth);
    }
    Random r = new Random();
    public void  generateObjects(){
        for (int i = 0; i<100; i++) {

            PerceptronSettings set = PerceptronSettings.createRandom(new int[]{19,19,5,3});
            addObject(new Piu(getRndX(), getRndY(), new NeuralBrain(new Perceptron(set)), this));
        }
        for(int times = 0; times<20; times++) {
         //   addObject(new RectangleObject(getRndX(), getRndY(), 40, 60, 0));
           // addObject(new RectangleObject(getRndX(), getRndY(), 50, 200, 1));
            addObject(new Donut(getRndX(), getRndY(), 50, 0));
            addObject(new Donut(getRndX(), getRndY(), 100, 0));
          //  addObject(new CircleObject(getRndX(), getRndY(), 100, 5));
            addObject(new Donut(getRndX(), getRndY(), 40, 0));
           // addObject(new CircleObject(getRndX(), getRndY(), 80, 1));

            for (int i = 0; i < 5; i++) {
             //   addObject(new CircleObject(getRndX(), getRndY(), 80,  1));
               // addObject(new CircleObject(getRndX(), getRndY(), 40, 6));
                addObject(new Donut(getRndX(), getRndY(), 10, 0));
               // addObject(new CircleObject(getRndX(), getRndY(), 5, 0));
            }
        }

        int wallWidth = 50;
        int offset = -100;
        //left vertical
        addObject(new Wall(0,offset,worldHeight-2*offset,wallWidth));
        //right vertical
        addObject(new Wall(worldWidth-wallWidth,offset,worldHeight-1-2*offset,wallWidth));
        //top horizontal
        addObject(new Wall(wallWidth+offset,0,wallWidth,worldHeight-2*wallWidth-2*offset));
        //bottom horizontal
        addObject(new Wall(wallWidth+offset,worldWidth-wallWidth,wallWidth,worldHeight-2*wallWidth-2*offset));

        addObject(new Wall(500,500,500,wallWidth));

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

    ArrayList<GameObject> objects = new ArrayList<>();

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


    public CollideLineResult collideLine(GameObject except, float x1, float y1, float angle, float lenght){
        return kinematic.collideLine(except,x1,y1,angle,lenght);
    }
}
