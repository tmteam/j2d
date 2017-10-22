package com.tutorial.game.Collisions;

import com.tutorial.game.GameObject;
import org.junit.experimental.theories.PotentialAssignment;

import java.awt.*;

public class SmartRectCollisionHandler {
    StupidRectCollisionHandler stupid = new StupidRectCollisionHandler();
    public  void Collide(GameObject origin, GameObject target){

        Rectangle targetBounds = target.getBounds();
        Rectangle originBounds = origin.getBounds();

        double targetRelativeCurrentX = target.getX()- origin.getX();
        double targetRelativeCurrentY = target.getY()- origin.getY();

        double targetRelativePreviousX = targetRelativeCurrentX + 10*(origin.getVelX() - target.getVelX());

        double targetRelativePreviousY = targetRelativeCurrentY + 10*(origin.getVelY() - target.getVelY());

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
            RightCollision(origin,target);
            return;
        }
        //Bottom
        if(CollisionTools.getIntersectionOrNull(
                targetRelativePreviousX, targetRelativePreviousY,
                targetRelativeCurrentX, targetRelativeCurrentY,
                right, bottom,
                left, bottom)!= null){
            BottomCollision(origin,target);
            return;
        }
        //left
        if(CollisionTools.getIntersectionOrNull(
                targetRelativePreviousX, targetRelativePreviousY,
                targetRelativeCurrentX, targetRelativeCurrentY,
                left,bottom,
                left, top)!=null){
            LeftCollision(origin,target);
            return;
        }
        //top
        if(CollisionTools.getIntersectionOrNull(
                targetRelativePreviousX, targetRelativePreviousY,
                targetRelativeCurrentX, targetRelativeCurrentY,
                left, top,
                right, top)!=null){
            TopCollision(origin,target);
            return;
        }

        stupid.Collide(origin,target);
    }
    public static final int  offset = 0;
    private void BottomCollision(GameObject origin,  GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);
       // target.setY((int)origin.getBounds().getMaxY() +offset);
       // System.out.println("Bottom Collision");

    }
    private void LeftCollision(GameObject origin, GameObject target) {
        Rectangle targetbounds = target.getBounds();
        CollisionTools.ExchangeHMassVelocity(origin, target);

        //target.setX( origin.getX()-  targetbounds.width- offset);
       // System.out.println("Left Collision");

    }

    private void TopCollision( GameObject origin,GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);

        //target.setY(origin.getY()- target.getBounds().height-offset);
       // System.out.println("Top Collision");

    }

    private void RightCollision(GameObject origin, GameObject target) {
        CollisionTools.ExchangeHMassVelocity(origin, target);
       // target.setX((int)origin.getBounds().getMaxX()+offset);
       // System.out.println("Right Collision");

    }
}
