package com.piu.game.Levels;

import com.piu.engine.ILevelHandler;
import com.piu.engine.IRenderable;

import java.awt.*;

public class GeneratingLevel implements ILevelHandler {
    private final GenerationResults results;
    private final IRenderable hid;
    private int width;
    private int height;

    public GeneratingLevel(GenerationResults results, IRenderable hid){
        this.results = results;
        this.hid = hid;
    }
    @Override
    public boolean IsLevelDone() {
        return false;
    }

    @Override
    public ILevelHandler getNextLevel() {
        return null;
    }

    @Override
    public void setScreenSize(int width, int height) {

        this.width = width;
        this.height = height;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0,0,width,height);
        g.setColor(Color.white);

        g.drawString("Generation Is Finished",200,20);
        g.drawString("Best fit  is: "+ results.getTickResult(),200,40);
        g.drawString("Generating new population",200,60);

        hid.render(g);

    }
}
