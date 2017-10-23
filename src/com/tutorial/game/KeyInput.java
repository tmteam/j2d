package com.tutorial.game;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class KeyInput extends KeyAdapter {

    private CameraCanvas cameraCanvas;

    public KeyInput(CameraCanvas cameraCanvas){
        this.cameraCanvas = cameraCanvas;

    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE)
            System.exit(0);
        if(key == KeyEvent.VK_MINUS)
            cameraCanvas.setScale((float) (cameraCanvas.getScale()*0.9));
        if(key == KeyEvent.VK_PLUS || key == KeyEvent.VK_EQUALS)
            cameraCanvas.setScale((float) (cameraCanvas.getScale()*1.1));
        if(key== KeyEvent.VK_LEFT)
            cameraCanvas.setxOffset(cameraCanvas.getxOffset()+100);
        if(key== KeyEvent.VK_RIGHT)
            cameraCanvas.setxOffset(cameraCanvas.getxOffset()-100);
        if(key== KeyEvent.VK_UP)
            cameraCanvas.setyOffset(cameraCanvas.getyOffset()+100);
        if(key== KeyEvent.VK_DOWN)
            cameraCanvas.setyOffset(cameraCanvas.getyOffset()-100);
    }
    public void  keyReleased(KeyEvent e){
        int key = e.getKeyCode();


    }
}
