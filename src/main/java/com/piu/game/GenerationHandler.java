package com.piu.game;
import com.piu.engine.*;

import com.piu.engine.Cameras.ICamera;
import com.piu.game.Pius.Piu;
import com.piu.game.Pius.PiuFactory;

import java.awt.*;


public class GenerationHandler implements IWorldHandler {

    private Hid hid;
    private WorldMap map;

    int piusCount = 0;
    int donutCount = 0;
    int tickcount = 0;

    LevelHandler handler;
    private int width;
    private int height;

    public GenerationHandler(ICamera camera, Hid hid, WorldMap map, PiuFactory[] generation, int donutsCount){
        this.hid = hid;
        this.map = map;
        Display display = new Display(new ShiftableCanvas(camera),  map.getHeight(), map.getWidth(),hid);
        handler = new LevelHandler(display,map.getWidth(), map.getHeight());

        map.DrawMap(this);
        for (PiuFactory factory: generation) {
            handler.addObjectAtRandomFreePlace(factory.createFor(this));
        }
        for(int times = 0; times<donutsCount; times++) {
            handler.addObjectAtRandomFreePlace(new Donut(0,0, 50, 0));
            donutCount++;
        }
    }


    public void setScreenSize(int width, int height){
        //this.width = width;
        //this.height = height;
        handler.setScreenSize(width,height);
    }


    public void tick(){
        handler.tick();
        tickcount++;
        if(tickcount>=100){
            hid.SetCurrentGameInfo(donutCount,piusCount);
            tickcount=0;
        }
    }
    public void render(Graphics g){
        handler.render(g);
    }

    public int getObjectCount(){
        return handler.getObjectCount();
    }



    public void  addObject(GameObject object){
        handler.addObject(object);
    }
    public void  removeObject(GameObject object){
       handler.removeObject(object);
    }

    public void  notifyPiusDeath(Piu target){
        piusCount--;
    }

    public void donutEaten(Donut donut){
        removeObject(donut);
        donutCount--;
    }

    public CollideLineResult collideLine(GameObject except, float x1, float y1, float angle, float lenght){
        return handler.collideLine(except,x1,y1,angle,lenght);
    }
}
