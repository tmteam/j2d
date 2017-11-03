package com.piu.engine;

import com.piu.engine.Keyboard.IKeyConcreteHandler;

public interface ILevelHandler extends IHandler {
    boolean IsLevelDone();
    ILevelHandler getNextLevel();
    IKeyConcreteHandler getKeyHandlerOrNull();
}
