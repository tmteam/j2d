package com.piu.engine.Keyboard;

import com.piu.engine.Cameras.ManualCamera;

import java.awt.event.KeyEvent;

public class CamerMoverConcreteKeyHandler implements IKeyConcreteHandler {

    private ManualCamera camera;

    public CamerMoverConcreteKeyHandler(ManualCamera camera){
        this.camera = camera;

    }
    @Override
    public boolean tryHandleKeyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_MINUS:
                camera.setScale((float) (camera.getScale()*0.9));
                return true;
            case KeyEvent.VK_EQUALS:
                camera.setScale((float) (camera.getScale()*1.1));
                return true;
            case KeyEvent.VK_LEFT:
                camera.setxOffset(camera.getxOffset()+100);
                return true;
            case KeyEvent.VK_RIGHT:
                camera.setxOffset(camera.getxOffset()-100);
                return true;
            case KeyEvent.VK_UP:
                camera.setyOffset(camera.getyOffset()+100);
                return true;
            case KeyEvent.VK_DOWN:
                camera.setyOffset(camera.getyOffset()-100);
                return true;
            default:
                return false;

        }
    }

    @Override
    public boolean tryHandleKeyReleased(KeyEvent e) {
        return false;
    }
}
