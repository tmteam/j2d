package com.tutorial.game.GameObjects.Pius;

import com.tutorial.game.GameObject;
import com.tutorial.game.ShiftableCanvas;

import java.awt.*;

public interface IPiuBehaviour {
    Color getMapColor();

    void tick();

    void render(ShiftableCanvas g, int radius, double angle);

    void afterCollisionWith(GameObject o);
}
