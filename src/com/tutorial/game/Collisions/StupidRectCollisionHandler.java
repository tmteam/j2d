package com.tutorial.game.Collisions;

import com.tutorial.game.GameObject;

import java.awt.*;

public class StupidRectCollisionHandler {



    public  void Collide(GameObject origin, GameObject target){

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
                    RightCollision(origin, target);
                else
                    BottomCollision(origin, target);
            }
            else if(dy<0)
            {
                double h = targetBounds.getMaxY() - origin.getY();
                double w = originBounds.getMaxX() - target.getX();

                if(h>w)
                    RightCollision(origin, target);
                else
                    TopCollision(origin,target);
            }
            else{
                RightCollision(origin, target);
            }
        } else if (dx <0){
            //top bottom left
            if(dy>0)
            {
                double h = originBounds.getMaxY() - target.getY();
                double w = targetBounds.getMaxX() - origin.getX();
                //bottom or left
                if(h>w)
                    LeftCollision(origin, target);
                else
                    BottomCollision(origin, target);
            }
            else if(dy<0) {
                //top or left
                double h =  targetBounds.getMaxY() - origin.getY();
                double w =  targetBounds.getMaxX() - origin.getX();

                if (h < w)
                    TopCollision(origin,target);
                else
                    LeftCollision(origin,  target);
            }
        }
        else{
            if(dy>0)
                BottomCollision(origin, target);
            else if(dy<0)
                TopCollision(origin,target);
        }
    }


    private void BottomCollision(GameObject origin,  GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);
        target.setY((int)origin.getBounds().getMaxY() + 4);
    }
    private void LeftCollision(GameObject origin, GameObject target) {
        Rectangle targetbounds = target.getBounds();
        CollisionTools.ExchangeHMassVelocity(origin, target);
        double previous = targetbounds.x;

        target.setX( origin.getX()-  targetbounds.width- 4);
    }

    private void TopCollision( GameObject origin,GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);

        target.setY(origin.getY()- target.getBounds().height-4);
    }

    private void RightCollision(GameObject origin, GameObject target) {
        CollisionTools.ExchangeHMassVelocity(origin, target);
        target.setX((int)origin.getBounds().getMaxX()+4);
    }
}
