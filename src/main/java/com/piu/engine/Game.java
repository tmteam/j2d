package com.piu.engine;

import com.piu.engine.Keyboard.KeyHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements  Runnable {
    public static  final  int WIDTH = 1200, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private IMainHandler handler;

    public Game(IMainHandler scenario){
        handler = scenario;
        new Window(WIDTH, HEIGHT, "HIHIHI", this);
        KeyHandler h = scenario.getKeyHandlerOrNull();
        if(h!=null)
            this.addKeyListener(h);
        scenario.setScreenSize(WIDTH, HEIGHT);

    }
    /*
    public Game() {
        ManualCamera camera = new ManualCamera();
        hid = new Hid(0,0);

        WorldMap map = new WorldMap(5000,5000, new WallDescription[]{
                new WallDescription(500,500,20,400)
        });

        int piuCount = 150;
        PiuFactory[] pius = new PiuFactory[150];
        for (int i = 0; i < piuCount; i++) {
            pius[i] = PiuFactory.createRandom(new int[]{19,8,3});
        }
        handler = new GenerationHandler(camera,hid,map,  pius, 150 );
        handler.setScreenSize(WIDTH, HEIGHT);

        //handler = new Handler(camera,hid,5000, 5000);
        new Window(WIDTH, HEIGHT, "HIHIHI", this);

       // HumanBrain player = new HumanBrain();

        //handler.addObjectAtRandomFreePlace(new Piu(300,300,player, handler));

        KeyHandler keyHandler = new KeyHandler();
        keyHandler.subscribe(new CamerMoverConcreteKeyHandler(camera));
       // keyHandler.subscribe(new PlayerMoverConcreteKeyHandler(player));
        this.addKeyListener(keyHandler);
    }*/

    public synchronized  void start(){
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop(){
        try {
            running = false;
            thread.join();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void  run(){
        long lasttime = System.nanoTime();
        int amountOfTicks = 120;
        double ns = 1000000000/(double)amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int framesPerSecond = 0;
        int ticksPerSeconnd = 0;

        while (running){
            long now = System.nanoTime();
            delta+=(now - lasttime)/ns;
            lasttime = now;
            while(delta>=1){
                handler.tick();
                int newTps = handler.getPreferedTps();

                if(newTps!=amountOfTicks) {
                    ns = 1000000000 / (double) newTps;
                    amountOfTicks = newTps;
                }

                ticksPerSeconnd++;
                delta--;
            }

            if(running)
                render();
            framesPerSecond++;
            if(System.currentTimeMillis() -timer>2000){
                timer+=2000;
                handler.setStatistic(framesPerSecond, ticksPerSeconnd);
                framesPerSecond = 0;
                ticksPerSeconnd=0;
            }
        }
        stop();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        handler.render(g);
        g.dispose();
        bs.show();
    }
}
