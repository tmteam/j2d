package com.piu.engine;

public interface ILevelHandler extends IHandler {
    boolean IsLevelDone();
    ILevelHandler getNextLevel();
}
