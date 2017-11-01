package com.piu.engine;

import java.awt.*;

public interface IHandler extends IRenderable {

    void setScreenSize(int width, int height);

    void tick();

    void render(Graphics g);
}
