package com.piu.game;

import com.genetics.CompactDMXGenomRepresentation;
import com.piu.engine.IRenderable;

import java.awt.*;
import java.util.ArrayList;

public class Hid implements IRenderable {
    private int top;
    private int left;
    private int fps;
    private int tps;
    private int ticks;
    private int donnutsCount;
    private int piusCount;
    private int bestEver;
    private int minimumDonuts = 100000;


    GenerationStatistic lastGeneration;

    public Hid(int top, int left){

        this.top = top;
        this.left = left;
    }
    public void  SetLastGenerationInfo(GenerationStatistic generationStatistic){
        this.lastGeneration = generationStatistic;
        bestEver =  Math.max(generationStatistic.bestFit, bestEver);
        minimumDonuts = Math.min(minimumDonuts, generationStatistic.dounutsLeft);
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

        int height = 420;
        g.setColor(Color.black);
        g.fillRect(top,left,110,height);
        g.setColor(Color.darkGray);
        ((Graphics2D)g).setStroke(new BasicStroke(4f));
        g.drawRect(top,left,110,height);
        g.setColor(Color.WHITE);
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add("fps: "+ fps);
        outputs.add("tps: "+ tps);
        outputs.add("------------");
        if(lastGeneration!=null) {
            outputs.add("gen: "+ (lastGeneration.previousGen +1));
            outputs.add("ticks: "+ ticks);
            outputs.add("donuts: "+ donnutsCount);
            outputs.add("pius: "+ piusCount);
            outputs.add("------------");
            outputs.add("best: " + lastGeneration.bestFit);
            outputs.add("average: " + lastGeneration.average);
            outputs.add("median: " + lastGeneration.median);
            outputs.add("don left: " + lastGeneration.dounutsLeft);

            if (lastGeneration.bestGenoms != null) {
                for (double[] genom : lastGeneration.bestGenoms) {
                    outputs.add(new CompactDMXGenomRepresentation(genom, 10).toString());
                }
            }
            outputs.add("------------");
            outputs.add("best: " + bestEver);
            outputs.add("min don left: " + minimumDonuts);
        }
        drawStrings(g, left+10, top+20, outputs);
    }
    private void drawStrings(Graphics g, int left, int top, ArrayList<String> lines ){
        for (int i = 0; i < lines.size(); i++) {
            g.drawString( lines.get(i),left,top+i*20);
        }
    }

}


