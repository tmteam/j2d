package com.piu.game;

import com.piu.engine.GameObject;
import com.piu.engine.GameObjects.CircleObject;
import com.piu.engine.ShiftableCanvas;
import com.piu.game.Pius.Piu;

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
    public void  tick(){
        this.velX = this.velX*0.99;
        this.velY = this.velY*0.99;
    }

    @Override
    public boolean tryCollideWith(GameObject o){
       // if(o instanceof PivaGamer){
       //     return true;
       // }
            if(o instanceof Piu){
                return true;
            }
        return super.tryCollideWith(o);
    }
}
