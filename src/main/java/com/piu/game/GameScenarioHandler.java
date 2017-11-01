package com.piu.game;

import com.piu.engine.Cameras.ICamera;
import com.piu.engine.Cameras.ManualCamera;
import com.piu.engine.GameObjects.WallDescription;
import com.piu.engine.IHandler;
import com.piu.engine.IMainHandler;
import com.piu.engine.Keyboard.CamerMoverConcreteKeyHandler;
import com.piu.engine.Keyboard.KeyHandler;
import com.piu.engine.WorldMap;
import com.piu.game.Pius.PiuFactory;

import java.awt.*;

public class GameScenarioHandler implements IMainHandler {

    private final Hid hid;
    private final ManualCamera camera;
    private GenerationHandler generationHandler;
    public GameScenarioHandler(){

        this.hid = new Hid(0,0);

        camera = new ManualCamera();

        WorldMap map = new WorldMap(5000,5000, new WallDescription[]{
                new WallDescription(500,500,20,400)
        });

        int piuCount = 150;
        PiuFactory[] pius = new PiuFactory[150];
        for (int i = 0; i < piuCount; i++) {
            pius[i] = PiuFactory.createRandom(new int[]{19,8,3});
        }
        generationHandler = new GenerationHandler(camera,hid,map,  pius, 150 );
        // HumanBrain player = new HumanBrain();

        //handler.addObjectAtRandomFreePlace(new Piu(300,300,player, handler));
    }
    @Override
    public void setScreenSize(int width, int height) {
        generationHandler.setScreenSize(width,height);
    }

    @Override
    public void tick() {
        generationHandler.tick();
    }

    @Override
    public void render(Graphics g) {
        generationHandler.render(g);
    }

    @Override
    public KeyHandler getKeyHandlerOrNull() {
        KeyHandler keyHandler = new KeyHandler();
        keyHandler.subscribe(new CamerMoverConcreteKeyHandler(camera));
        return keyHandler;
    }

    @Override
    public void setStatistic(int fps, int tps) {
        hid.SetCurrentGraphInfo(fps,tps,0);
    }

    @Override
    public int getPreferedTps() {
        return 60;
    }
}
