package com.tutorial.game.Collisions;

import com.tutorial.game.GameObject;
import org.junit.experimental.theories.PotentialAssignment;

import java.awt.*;

public class SmartRectCollisionHandler {
    StupidRectCollisionHandler stupid = new StupidRectCollisionHandler();
    public double Collide(GameObject origin, GameObject target){

        Rectangle targetBounds = target.getBounds();
        Rectangle originBounds = origin.getBounds();
        double dvx=origin.getVelX() - target.getVelX();
        double dvy=origin.getVelY() - target.getVelY();
        double targetRelativeCurrentX = target.getX()- origin.getX(); //- dvx/2;
        double targetRelativeCurrentY = target.getY()- origin.getY(); //- dvy/2;

        double targetRelativePreviousX = targetRelativeCurrentX + 10*dvx;

        double targetRelativePreviousY = targetRelativeCurrentY + 10*dvy;

        double right = originBounds.width;
        double top = - targetBounds.height;
        double bottom = originBounds.height;
        double left = -targetBounds.width;

        //Right check:
        if(CollisionTools.getIntersectionOrNull(
                targetRelativePreviousX, targetRelativePreviousY,
                targetRelativeCurrentX, targetRelativeCurrentY,
                right,top,
                right,bottom)!= null){
            if(target.getVelX()<= origin.getVelX())
                RightCollision(origin,target);
            else
                LeftCollision(origin,target);
            return 90;
        }
        //Bottom
        if(CollisionTools.getIntersectionOrNull(
                targetRelativePreviousX, targetRelativePreviousY,
                targetRelativeCurrentX, targetRelativeCurrentY,
                right, bottom,
                left, bottom)!= null){

            if(target.getVelY()<= origin.getVelY())
                BottomCollision(origin,target);
             else
                TopCollision(origin,target);
            return 180;
        }
        //left
        if(CollisionTools.getIntersectionOrNull(
                targetRelativePreviousX, targetRelativePreviousY,
                targetRelativeCurrentX, targetRelativeCurrentY,
                left,bottom,
                left, top)!=null){


            if(target.getVelX()>= origin.getVelX())
                LeftCollision(origin,target);
            else
                RightCollision(origin,target);


            return 270;
        }
        //top
        if(CollisionTools.getIntersectionOrNull(
                targetRelativePreviousX, targetRelativePreviousY,
                targetRelativeCurrentX, targetRelativeCurrentY,
                left, top,
                right, top)!=null){
            if(target.getVelY()>= origin.getVelY())

                TopCollision(origin,target);
            else

                BottomCollision(origin,target);
            return 0;
        }

        return 0;// stupid.Collide(origin,target);
    }
    int offset = 0;
    private void BottomCollision(GameObject origin,  GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);
        target.setY((int)origin.getBounds().getMaxY() +offset);
        if(origin.getVelY()>target.getVelY()){
            System.out.println("Smart Bottom failed");
        }
    }
    private void LeftCollision(GameObject origin, GameObject target) {
        Rectangle targetbounds = target.getBounds();
        CollisionTools.ExchangeHMassVelocity(origin, target);

        target.setX( origin.getX()-  targetbounds.width- offset);
       // System.out.println("Left Collision");

    }

    private void TopCollision( GameObject origin,GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);
        target.setY(origin.getY()- target.getBounds().height-offset);
       // System.out.println("Top Collision");

    }

    private void RightCollision(GameObject origin, GameObject target) {
        CollisionTools.ExchangeHMassVelocity(origin, target);
        target.setX((int)origin.getBounds().getMaxX()+offset);
       // System.out.println("Right Collision");

    }
}
