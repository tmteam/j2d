package com.tutorial.game.GameObjects;

import com.tutorial.game.GameObject;

public class Donut extends CircleObject {
    public Donut(double x, double y, int radius, int velocity) {
        super(x, y, radius, velocity);
    }
    @Override
    public boolean tryCollideWith(GameObject o){
       // if(o instanceof PivaGamer){
       //     return true;
       // }
        return super.tryCollideWith(o);
    }
}
