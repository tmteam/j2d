package com.piu.game;

import com.piu.engine.IRenderable;

import java.awt.*;

public class Hid implements IRenderable {
    private int top;
    private int left;
    private int fps;
    private int tps;
    private int ticks;
    private int donnutsCount;
    private int piusCount;

    public Hid(int top, int left){

        this.top = top;
        this.left = left;
    }
    public void  SetCurrentGameInfo(int donnutsCount, int piusCount){

        this.donnutsCount = donnutsCount;
        this.piusCount = piusCount;
    }
    public void  SetCurrentGraphInfo(int fps, int tps, int ticks){

        this.fps = fps;
        this.tps = tps;
        this.ticks = ticks;
    }

    public void render(Graphics g){

        g.setColor(Color.black);
        g.fillRect(top,left,100,200);
        g.setColor(Color.darkGray);
        ((Graphics2D)g).setStroke(new BasicStroke(4f));
        g.drawRect(top,left,100,200);
        g.setColor(Color.WHITE);

        g.drawString( "fps: "+ fps,left+15,top+20);
        g.drawString( "tps: "+ tps,left+15,top+40);
        g.drawString( "ticks: "+ ticks,left+15,top+60);

        g.drawString( "donuts: "+ donnutsCount,left+15,top+80);
        g.drawString( "pius: "+ piusCount,left+15,top+100);

    }
}
