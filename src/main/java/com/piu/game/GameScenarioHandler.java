package com.piu.game;

import com.genetics.SingleGenomCombinedMutator;
import com.neural.PerceptronSettings;
import com.piu.engine.Cameras.ICamera;
import com.piu.engine.Cameras.ManualCamera;
import com.piu.engine.GameObjects.WallDescription;
import com.piu.engine.IHandler;
import com.piu.engine.ILevelHandler;
import com.piu.engine.IMainHandler;
import com.piu.engine.Keyboard.CamerMoverConcreteKeyHandler;
import com.piu.engine.Keyboard.IKeyConcreteHandler;
import com.piu.engine.Keyboard.KeyHandler;
import com.piu.engine.WorldMap;
import com.piu.game.Levels.GenerationLevel;
import com.piu.game.Pius.PiuFactory;

import java.awt.*;

public class GameScenarioHandler implements IMainHandler {

    private final Hid hid;
    private ILevelHandler levelHandler;
    private int width;
    private int height;
    private KeyHandler keyHandler;
    public GameScenarioHandler(){

        keyHandler = new KeyHandler();

        this.hid = new Hid(0,0);


        WorldMap map = new WorldMap(14000,14000, new WallDescription[]{
                new WallDescription(500,900,20,5000),
                new WallDescription(500,1900,20,5000),

                new WallDescription(8000,500,1000,40),

                new WallDescription(8200,900,1000,40),

                new WallDescription(6000,6000,2000,2000),
                new WallDescription(8000,900,40,2000),
                new WallDescription(10000,10000,3000,30),
                new WallDescription(900,8000,20,3000),

                new WallDescription(10000,3000,2000,2000),
                new WallDescription(10000,900,40,2000),
        });

        int piuCount = 150;
        PiuFactory[] pius = new PiuFactory[150];
        SingleGenomCombinedMutator mutator = new SingleGenomCombinedMutator();

        int[] layers = new int[]{19, 19, 8,3};
        for (int i = 0; i < piuCount; i++) {
            PerceptronSettings ps = PerceptronSettings.createRandom(layers);

            double[] genom =  ps.toGenom();
            for (int j = 0; j < 20; j++) {
                mutator.mutate(genom);
            }

            pius[i] = PiuFactory.createFor(PerceptronSettings.create(layers, genom));
        }
        levelHandler = new GenerationLevel(1,hid,map,  pius, 200 );
        IKeyConcreteHandler kh = levelHandler.getKeyHandlerOrNull();
        if(kh!=null)
            keyHandler.subscribe(kh);
        // HumanBrain player = new HumanBrain();
        //handler.addObjectAtRandomFreePlace(new Piu(300,300,player, handler));
    }

    @Override
    public void setScreenSize(int width, int height) {
        this.width = width;
        this.height = height;
        levelHandler.setScreenSize(width,height);
    }

    @Override
    public void tick() {
        levelHandler.tick();
        if(levelHandler.IsLevelDone()){
            IKeyConcreteHandler kh = levelHandler.getKeyHandlerOrNull();

            if(kh!=null)
                keyHandler.unsubscribe(kh);
            levelHandler = levelHandler.getNextLevel();

            IKeyConcreteHandler nh = levelHandler.getKeyHandlerOrNull();
            if(nh!=null)
                keyHandler.subscribe(nh);

            levelHandler.setScreenSize(width,height);

        }
    }

    @Override
    public void render(Graphics g) {
        levelHandler.render(g);
    }

    @Override
    public KeyHandler getKeyHandlerOrNull() {
        return keyHandler;
    }

    @Override
    public void setStatistic(int fps, int tps) {
        hid.SetCurrentGraphInfo(fps,tps);
    }

    @Override
    public int getPreferedTps() {
        return 250;
    }
}
