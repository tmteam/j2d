package com.tutorial.game.Collisions;

import com.tutorial.game.GameObject;

import java.awt.*;

public class StupidRectCollisionHandler {

    public static final int  offset = 0;


    public  double Collide(GameObject origin, GameObject target){

        Rectangle targetBounds = target.getBounds();
        Rectangle originBounds = origin.getBounds();

        double originPreviousX  = originBounds.x + originBounds.width/2 -  origin.getVelX();
        double originPreviousY  = originBounds.y + originBounds.height/2 - origin.getVelX();


        double targetPreviousX  = targetBounds.x + targetBounds.width/2 - target.getVelX();
        double targetPreviousY  = targetBounds.y + targetBounds.height/2 - target.getVelY();

        double dx = targetPreviousX -originPreviousX;
        double dy = targetPreviousY -originPreviousY;

        if(dx>0){
            //top bottom right
            if(dy>0)
            {
                double h = originBounds.getMaxY() - target.getY();
                double w = originBounds.getMaxX() - target.getX();
                if(h>w)
                    return  RightCollision(origin, target);
                else
                    return  BottomCollision(origin, target);
            }
            else if(dy<0)
            {
                double h = targetBounds.getMaxY() - origin.getY();
                double w = originBounds.getMaxX() - target.getX();

                if(h>w)
                    return  RightCollision(origin, target);
                else
                    return  TopCollision(origin,target);
            }
            else{
                return  RightCollision(origin, target);
            }
        } else if (dx <0){
            //top bottom left
            if(dy>0)
            {
                double h = originBounds.getMaxY() - target.getY();
                double w = targetBounds.getMaxX() - origin.getX();
                //bottom or left
                if(h>w)
                    return  LeftCollision(origin, target);
                else
                    return   BottomCollision(origin, target);
            }
            else if(dy<0) {
                //top or left
                double h =  targetBounds.getMaxY() - origin.getY();
                double w =  targetBounds.getMaxX() - origin.getX();

                if (h < w)
                    return  TopCollision(origin,target);
                else
                    return  LeftCollision(origin,  target);
            }
        }
        else{
            if(dy>0)
                return   BottomCollision(origin, target);
            else if(dy<0)
              return   TopCollision(origin,target);
        }
        return 0;
    }


    private double BottomCollision(GameObject origin,  GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);
        target.setY((int)origin.getBounds().getMaxY() + offset);
        return 180;
    }
    private double LeftCollision(GameObject origin, GameObject target) {
        Rectangle targetbounds = target.getBounds();
        CollisionTools.ExchangeHMassVelocity(origin, target);

        target.setX( origin.getX()-  targetbounds.width- offset);
        return 270;
    }

    private double TopCollision( GameObject origin,GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);

        target.setY(origin.getY()- target.getBounds().height-offset);
        return 0;
    }

    private double RightCollision(GameObject origin, GameObject target) {
        CollisionTools.ExchangeHMassVelocity(origin, target);
        target.setX((int)origin.getBounds().getMaxX()+offset);
        return 90;
    }
}
