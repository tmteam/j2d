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

    private int previousGen;
    private int lastGenBestFit;
    private int lastGenDonnutsEaten;
    private int lastGenMedian;
    private int lastGenAverage;
    private int bestEver;

    public Hid(int top, int left){

        this.top = top;
        this.left = left;
    }
    public void  SetLastGenerationInfo(int previousGen, int bestFit, int donutsEaten, int median, int average){
        this.previousGen = previousGen;
        this.lastGenBestFit = bestFit;
        this.lastGenDonnutsEaten = donutsEaten;
        this.lastGenAverage = average;
        this.lastGenMedian = median;
        bestEver =  Math.max(bestFit, bestEver);
    }
    public void  SetCurrentGameInfo(int donnutsCount, int piusCount,int generationTick){

        this.donnutsCount = donnutsCount;
        this.piusCount = piusCount;
        this.ticks = generationTick;
    }
    public void  SetCurrentGraphInfo(int fps, int tps){

        this.fps = fps;
        this.tps = tps;
    }

    public void render(Graphics g){

        g.setColor(Color.black);
        g.fillRect(top,left,100,280);
        g.setColor(Color.darkGray);
        ((Graphics2D)g).setStroke(new BasicStroke(4f));
        g.drawRect(top,left,100,280);
        g.setColor(Color.WHITE);

        drawStrings(g, left+15, top+20,new String[]{
                "fps: "+ fps,
                "tps: "+ tps,
                "------------",
                "gen: "+ (int)(previousGen+1),
                "ticks: "+ ticks,
                "donuts: "+ donnutsCount,
                "pius: "+ piusCount,
                "------------",
                "best: "+ lastGenBestFit,
                "average: "+ lastGenAverage,
                "median: "+ lastGenMedian,
                "------------",
                "bestever: "+ bestEver,

        });
    }
    private void drawStrings(Graphics g, int left, int top, String[] lines ){
        for (int i = 0; i < lines.length; i++) {
            g.drawString( lines[i],left,top+i*20);
        }
    }
}
