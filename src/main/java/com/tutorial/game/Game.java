package com.tutorial.game;

import com.tutorial.game.Cameras.ManualCamera;
import com.tutorial.game.GameObjects.Pius.Piu;
import com.tutorial.game.GameObjects.Pius.HumanBrain;
import com.tutorial.game.Keyboard.CamerMoverConcreteKeyHandler;
import com.tutorial.game.Keyboard.KeyHandler;
import com.tutorial.game.Keyboard.PlayerMoverConcreteKeyHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements  Runnable {
    public static  final  int WIDTH = 800, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Hid hid;
    public Game() {
        ManualCamera camera = new ManualCamera();
        hid = new Hid(0,0);
        handler = new Handler(camera,hid,5000, 5000);
        handler.setScreenSize(WIDTH, HEIGHT);
        new Window(WIDTH, HEIGHT, "HIHIHI", this);
        handler.generateObjects();

        HumanBrain player = new HumanBrain();

        handler.addObject(new Piu(400,400,player, handler));

        KeyHandler keyHandler = new KeyHandler();
        keyHandler.subscribe(new CamerMoverConcreteKeyHandler(camera));
        keyHandler.subscribe(new PlayerMoverConcreteKeyHandler(player));
        this.addKeyListener(keyHandler);


    }

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
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int framesPerSecond = 0;
        int ticksPerSeconnd = 0;
        int ticks = 0;

        while (running){
            long now = System.nanoTime();
            delta+=(now - lasttime)/ns;
            lasttime = now;
            while(delta>=1){
                tick();
                ticks++;
                ticksPerSeconnd++;
                delta--;
            }
            if(running)
                render();
            framesPerSecond++;
            if(System.currentTimeMillis() -timer>2000){
                timer+=2000;
                hid.SetCurrentGraphInfo(framesPerSecond,ticksPerSeconnd,ticks);
                framesPerSecond = 0;
                ticksPerSeconnd=0;
            }
        }
        stop();
    }
    private void tick(){
        handler.tick();
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
