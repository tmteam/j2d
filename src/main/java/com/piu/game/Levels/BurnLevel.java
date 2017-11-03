package com.piu.game.Levels;

import com.genetics.*;
import com.neural.PerceptronSettings;
import com.piu.engine.ILevelHandler;
import com.piu.engine.Keyboard.IKeyConcreteHandler;
import com.piu.engine.WorldMap;
import com.piu.game.Hid;
import com.piu.game.Pius.NeuralBrain;
import com.piu.game.Pius.PiuFactory;

import java.awt.*;

public class BurnLevel implements ILevelHandler, Runnable {
    private final WorldMap map;
    private final GenerationResults results;
    private final Hid hid;
    private int width;
    private int height;
    private Thread thread;
    private boolean started;
    private ILevelHandler nextLevel;

    public BurnLevel(WorldMap map, GenerationResults results, Hid hid){
        this.map = map;
        this.results = results;
        this.hid = hid;
    }
    @Override
    public boolean IsLevelDone() {
        return nextLevel!=null;
    }

    @Override
    public ILevelHandler getNextLevel() {
        return nextLevel;
    }

    @Override
    public IKeyConcreteHandler getKeyHandlerOrNull() {
        return null;
    }

    @Override
    public void setScreenSize(int width, int height) {

        this.width = width;
        this.height = height;
    }

    @Override
    public void tick() {
        if(!started)
            start();
    }

    public synchronized  void start(){
        thread = new Thread(this);
        started = true;
        nextLevel = null;
        thread.start();
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

    @Override
    public void run() {

        int piusCount = results.getPiuResults().size();
        int tickRes = results.getTickResult();
        int median = (int)results.getPiuResults().get(piusCount/2).fitness;
        double summFit = 0;
        double bestFit = 0;
        for (PiuResult res :
                results.getPiuResults())
        {
            summFit+=res.fitness;
            bestFit = Math.max(res.fitness, bestFit);
        }
        double average = summFit/piusCount;

        hid.SetLastGenerationInfo(results.getGenerationNum(),(int)bestFit,0,median,(int)average);

        GenomFitness[] gf = new GenomFitness[piusCount];
        PerceptronSettings settings = null;
        for (int i = 0; i < piusCount; i++) {
            PiuResult result = results.getPiuResults().get(i);
            settings = ((NeuralBrain)result.piu.getBrain()).getSettings();
            double[] genom = settings.toGenom();
            gf[i] = new GenomFitness(genom, result.fitness);
        }
        GenderPopulationMutator mutator =new GenderPopulationMutator();
        Genom[] newPopulationGenom = mutator.Mutate(gf);

        PiuFactory[] newPopulation = new PiuFactory[newPopulationGenom.length];
        for (int i = 0; i < newPopulation.length; i++) {
            newPopulation[i] =
                    PiuFactory.createFor(
                            PerceptronSettings.create(settings.getAllLayers(),
                            newPopulationGenom[i].getVals()));
        }


        nextLevel = new GenerationLevel(results.getGenerationNum()+1,hid,map,newPopulation,250);
    }
}
