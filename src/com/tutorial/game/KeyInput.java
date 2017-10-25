package com.tutorial.game;

import com.tutorial.game.Cameras.ManualCamera;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private ManualCamera camera;

    public KeyInput(ManualCamera camera){
        this.camera = camera;

    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE)
            System.exit(0);
        if(key == KeyEvent.VK_MINUS)
            camera.setScale((float) (camera.getScale()*0.9));
        if(key == KeyEvent.VK_PLUS || key == KeyEvent.VK_EQUALS)
            camera.setScale((float) (camera.getScale()*1.1));
        if(key== KeyEvent.VK_LEFT)
            camera.setxOffset(camera.getxOffset()+100);
        if(key== KeyEvent.VK_RIGHT)
            camera.setxOffset(camera.getxOffset()-100);
        if(key== KeyEvent.VK_UP)
            camera.setyOffset(camera.getyOffset()+100);
        if(key== KeyEvent.VK_DOWN)
            camera.setyOffset(camera.getyOffset()-100);
    }
    public void  keyReleased(KeyEvent e){
        int key = e.getKeyCode();


    }
}
