package com.piu.engine;

import java.awt.*;
import java.util.List;

public class Display {

    private ShiftableCanvas canvas;
    private IRenderable hid;
    private int worldWidth = 2000;
    private int worldHeight = 2000;
    private int screenWidth = 600;
    private int screenHeight = 800;
    public Display(ShiftableCanvas canvas, int worldWidth, int woldHeight, IRenderable hid){
        this.worldWidth = worldWidth;
        this.worldHeight = woldHeight;
        this.canvas = canvas;
        this.hid = hid;
    }
    public void setScreenSize(int screenWidth, int screenHeight)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
    public void Render(Graphics g, List<GameObject> renderableObjects){
        canvas.setCurrentGraphics(g);
        //prepare graphics
        g.setColor(new Color(25,25,25));
        g.fillRect(0,0,screenWidth,screenHeight);

        //Prepare canvas with map preferences
        canvas.setColor(Color.black);

        canvas.fillRect(0,0,worldWidth,worldHeight);
        g.setColor(new Color(20,20,20));

        for(int x = 0; x< worldWidth; x+=50){
            canvas.drawLine(x,0,x,worldHeight);
        }

        for(int y = 0; y< worldHeight; y+=50){
            canvas.drawLine(0,y,worldWidth,y);
        }

        //canvas.setColor(Color.MAGENTA);
        //canvas.drawRect(0,0,worldWidth,worldHeight);

        //Paint objects
        for (int i = 0; i< renderableObjects.size(); i++) {
            GameObject obj = renderableObjects.get(i);
            if(obj!=null) {
                canvas.setPaintingFor(obj);
                obj.render(canvas);
            }
        }
        //Paint hid
        hid.render(g);
    }
}
