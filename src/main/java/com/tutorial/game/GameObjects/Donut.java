package com.tutorial.game.GameObjects;

import com.tutorial.game.GameObject;
import com.tutorial.game.ShiftableCanvas;

import java.awt.*;

public class Donut extends CircleObject {
    public Donut(double x, double y, int radius, int velocity) {
        super(x, y, radius, velocity);
    }

    @Override
    public void render(ShiftableCanvas canvas){
        canvas.setStroke(radius/2);
        canvas.setColor(Color.orange);// new Color(0x6495ED));
        canvas.drawCircle(radius,radius,radius);
    }

    @Override
    public boolean tryCollideWith(GameObject o){
       // if(o instanceof PivaGamer){
       //     return true;
       // }
        return super.tryCollideWith(o);
    }
}
