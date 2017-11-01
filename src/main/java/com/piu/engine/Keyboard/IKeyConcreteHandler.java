package com.piu.engine.Keyboard;

import java.awt.event.KeyEvent;

public interface IKeyConcreteHandler {
   boolean tryHandleKeyPressed(KeyEvent e);
   boolean tryHandleKeyReleased(KeyEvent e);
}
