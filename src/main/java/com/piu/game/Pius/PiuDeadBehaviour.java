package com.piu.game.Pius;

import com.piu.engine.GameObject;
import com.piu.engine.ShiftableCanvas;

import java.awt.*;

public class PiuDeadBehaviour implements IPiuBehaviour {

    @Override
    public Color getMapColor() {
        return Color.blue;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(ShiftableCanvas g, int radius, double angle) {
        int diameter = radius * 2;
        Color base = Color.blue;

        g.setStroke(1);

        g.setColor(base);
        g.setStroke(radius / 5);
        g.drawOval(0, 0, diameter, diameter);
        int ang = (int) (angle * 180 / Math.PI);

        g.fillArc(radius, radius, radius, ang + 160, 40);
    }

    @Override
    public void afterCollisionWith(GameObject o) {

    }
}
