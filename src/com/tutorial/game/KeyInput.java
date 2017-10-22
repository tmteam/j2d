package com.tutorial.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
   /*
    private Handler handler;

    public KeyInput(Handler handler){

        this.handler = handler;
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE)
            System.exit(0);
       for (GameObject p: handler.objects) {
            if(p.getId()== ID.Player){
                //player1 Events
                if(key== KeyEvent.VK_W)
                    p.setVelY(-5);
                if(key== KeyEvent.VK_A)
                    p.setVelX(-5);
                if(key== KeyEvent.VK_S)
                    p.setVelY(5);
                if(key== KeyEvent.VK_D)
                    p.setVelX(5);
            }
        }

    }
    public void  keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (GameObject p: handler.objects) {
            if(p.getId()== ID.Player){
                if(key== KeyEvent.VK_W)
                    p.setVelY(0);
                if(key== KeyEvent.VK_A)
                    p.setVelX(0);
                if(key== KeyEvent.VK_S)
                    p.setVelY(0);
                if(key== KeyEvent.VK_D)
                    p.setVelX(0);
            }
        }
    }*/
}
