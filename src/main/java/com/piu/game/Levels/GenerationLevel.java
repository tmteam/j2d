package com.piu.game.Levels;

import com.piu.engine.*;
import com.piu.engine.Cameras.ICamera;
import com.piu.game.Donut;
import com.piu.game.Hid;
import com.piu.game.Pius.Piu;
import com.piu.game.Pius.PiuFactory;

import java.awt.*;
import java.util.ArrayList;

public class GenerationLevel implements ISurfaceWorldHandler, ILevelHandler {

    private Hid hid;
    private WorldMap map;

    int piusCount = 0;
    int donutCount = 0;
    int tickcount = 0;

    SurfaceWorldHandler handler;
    private int width;
    private int height;
    private ArrayList<PiuResult> results;
    public GenerationLevel(ICamera camera, Hid hid, WorldMap map, PiuFactory[] generation, int donutsCount){
        this.hid = hid;
        this.map = map;
        Display display = new Display(new ShiftableCanvas(camera),  map.getHeight(), map.getWidth(),hid);
        handler = new SurfaceWorldHandler(display,map.getWidth(), map.getHeight());

        map.DrawMap(this);
        for (PiuFactory factory: generation) {
            if(handler.addObjectAtRandomFreePlace(factory.createFor(this)));
            piusCount++;
        }
        for(int times = 0; times<donutsCount; times++) {
            handler.addObjectAtRandomFreePlace(new Donut(0,0, 50, 0));
            donutCount++;
        }
        results = new ArrayList<>(generation.length);
    }


    public void setScreenSize(int width, int height){
        handler.setScreenSize(width,height);
    }
    GenerationResults generationResults = null;
    int generationLength = 0;

    public void tick(){
        handler.tick();
        tickcount++;
        generationLength++;
        if(tickcount>=100){
            hid.SetCurrentGameInfo(donutCount,piusCount, generationLength);
            tickcount=0;
        }
        if(IsLevelDone() && generationResults==null){
            generationResults = new GenerationResults(results,generationLength);

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
        results.add(new PiuResult(target,tickcount));
        piusCount--;
    }

    public void donutEaten(Donut donut){
        removeObject(donut);
        donutCount--;
    }

    public CollideLineResult collideLine(GameObject except, float x1, float y1, float angle, float lenght){
        return handler.collideLine(except,x1,y1,angle,lenght);
    }

    @Override
    public boolean IsLevelDone() {
        return piusCount<=0;
    }

    @Override
    public ILevelHandler getNextLevel() {
        return new GeneratingLevel(generationResults,hid);
    }
}
