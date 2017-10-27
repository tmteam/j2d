package com.tutorial.game.Keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyHandler extends KeyAdapter {

    LinkedList<IKeyConcreteHandler> handlers = new LinkedList<>();

    public KeyHandler(){

    }

    public void  subscribe(IKeyConcreteHandler handler){
        handlers.add(handler);
    }
    public void  unsubscribe(IKeyConcreteHandler handler){
        handlers.remove(handler);
    }

    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e) {
        for( IKeyConcreteHandler handler: handlers)
            if (handler.tryHandleKeyPressed(e))
                break;
    }

    /**
     * Invoked when a key has been released.
     */
    public void keyReleased(KeyEvent e) {
        for( IKeyConcreteHandler handler: handlers)
            if (handler.tryHandleKeyReleased(e))
                break;
    }

}
