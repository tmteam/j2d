package com.piu.engine;

import com.piu.engine.Keyboard.KeyHandler;

public interface IMainHandler  extends IHandler{

    KeyHandler getKeyHandlerOrNull();
    void setStatistic(int fps, int tps);
    int getPreferedTps();

}
