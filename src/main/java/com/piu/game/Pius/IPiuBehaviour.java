package com.piu.game.Pius;

import com.piu.engine.GameObject;
import com.piu.engine.ShiftableCanvas;

import java.awt.*;

public interface IPiuBehaviour {
    Color getMapColor();

    void tick();

    void render(ShiftableCanvas g, int radius, double angle);

    void afterCollisionWith(GameObject o);
}
