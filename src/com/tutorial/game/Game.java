package com.tutorial.game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements  Runnable {
    public static  final  int WIDTH = 800, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    KeyInput input;
    private CameraCanvas cameraCanvas;
    public Game() {
        cameraCanvas = new CameraCanvas();
        input = new KeyInput(cameraCanvas);
        handler = new Handler();
        this.addKeyListener(input);
        new Window(WIDTH, HEIGHT, "HIHIHI", this);
        Random r = new Random();
        handler.addObject(new RectangleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 40, 60,  handler, 1));
        handler.addObject(new RectangleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 50, 200, handler, 1));
        handler.addObject(new CircleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 40, handler, 2));
        handler.addObject(new CircleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 100, handler, 0));
        handler.addObject(new CircleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 100, handler, 0));
        handler.addObject(new CircleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 40, handler, 0));
        handler.addObject(new CircleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 80, handler, 1));

        for(int i = 0; i< 5; i++) {
            handler.addObject(new CircleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 80, handler, 1));
            handler.addObject(new CircleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 40, handler, 0));
        }
        handler.addObject(new CircleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 10, handler, 0));

        // for (int i = 1; i < 15; i++) {
        //      handler.addObject(new CircleObject(r.nextInt(WIDTH), r.nextInt(HEIGHT), 10+i*3, handler, 0));
       // }
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

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH, HEIGHT);
        cameraCanvas.setCurrentGraphics(g);
        handler.render(cameraCanvas);

        g.dispose();
        bs.show();
    }
}
