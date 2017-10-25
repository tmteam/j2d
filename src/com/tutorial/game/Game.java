package com.tutorial.game;

import com.tutorial.game.Cameras.ManualCamera;
import com.tutorial.game.GameObjects.CircleObject;
import com.tutorial.game.GameObjects.RectangleObject;
import com.tutorial.game.GameObjects.Wall;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements  Runnable {
    public static  final  int WIDTH = 800, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    KeyInput input;
    public Game() {
        ManualCamera camera = new ManualCamera();

        input = new KeyInput(camera);
        handler = new Handler(camera,2000, 2000);
        handler.setScreenSize(WIDTH, HEIGHT);
        this.addKeyListener(input);

        new Window(WIDTH, HEIGHT, "HIHIHI", this);

        handler.generateObjects();
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
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta+=(now - lasttime)/ns;
            lasttime = now;
            while(delta>=1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
            if(System.currentTimeMillis() -timer>2000){
                timer+=2000;
                System.out.println("FPS: "+ frames+" Count: "+ handler.getObjectCount());
                frames = 0;
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
