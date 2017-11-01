package com.piu.engine;

import com.piu.engine.GameObjects.Wall;
import com.piu.engine.GameObjects.WallDescription;

public class WorldMap {

    private final int height;
    private final int width;
    private final WallDescription[] walls;

    public WorldMap(int height, int width, WallDescription[] walls){

        this.height = height;
        this.width = width;
        this.walls = walls;
    }
    public int getHeight(){return height;}
    public int getWidth(){return  width;}

    public void  DrawMap(IWorldHandler handler){

        int wallWidth = 50;
        int offset = -100;
        //left vertical
        handler.addObject(new Wall(0,offset,height-2*offset,wallWidth));
        //right vertical
        handler.addObject(new Wall(width-wallWidth,offset,height-1-2*offset,wallWidth));
        //top horizontal
        handler.addObject(new Wall(wallWidth+offset,0,wallWidth,height-2*wallWidth-2*offset));
        //bottom horizontal
        handler.addObject(new Wall(wallWidth+offset,width-wallWidth,wallWidth,height-2*wallWidth-2*offset));

        for (WallDescription wall: walls) {
            handler.addObject(new Wall(wall.x,wall.y,wall.height,wall.width));
        }
    }
}

