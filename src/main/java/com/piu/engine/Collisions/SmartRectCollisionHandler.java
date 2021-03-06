package com.piu.engine.Collisions;

import com.piu.engine.GameObject;

import java.awt.*;

public class SmartRectCollisionHandler {
    StupidRectCollisionHandler stupid = new StupidRectCollisionHandler();

    public double Collide(GameObject origin, GameObject target){
        Rectangle targetBounds = target.getBounds();
        Rectangle originBounds = origin.getBounds();

        double dvx=origin.getVelX() - target.getVelX();
        double dvy=origin.getVelY() - target.getVelY();

        double targetRelativeCurrentX = target.getX()- origin.getX()- dvx/2;
        double targetRelativeCurrentY = target.getY()- origin.getY()- dvy/2;

        double targetRelativePreviousX = targetRelativeCurrentX + 10*dvx;

        double targetRelativePreviousY = targetRelativeCurrentY + 10*dvy;

        double right = originBounds.width;
        double top = - targetBounds.height;
        double bottom = originBounds.height;
        double left = -targetBounds.width;

        CollideCalculationResult collider = new CollideCalculationResult(
                targetRelativePreviousX, targetRelativePreviousY,
                targetRelativeCurrentX, targetRelativeCurrentY);

        collider.checkSide(ColideSide.right, right,top, right,bottom);
        collider.checkSide(ColideSide.left, left,bottom,left, top);
        collider.checkSide(ColideSide.bottom, right, bottom,left, bottom);
        collider.checkSide(ColideSide.top, left, top, right, top);

        switch (collider.bestSide) {
            case right: {
                if (target.getVelX() <= origin.getVelX())
                    RightCollision(origin, target);
                else
                    LeftCollision(origin, target);
                return 90;
            }
            case bottom: {
                if (target.getVelY() <= origin.getVelY())
                    BottomCollision(origin, target);
                else
                    TopCollision(origin, target);
                return 180;
            }
            case left: {
                if (target.getVelX() >= origin.getVelX())
                    LeftCollision(origin, target);
                else
                    RightCollision(origin, target);

                return 270;
            }
            case top: {
                if (target.getVelY() >= origin.getVelY())
                    TopCollision(origin, target);
                else
                    BottomCollision(origin, target);
                return 0;
            }
            default: {
               // System.out.println("Collide failed");
                return 0;// stupid.Collide(origin,target);
            }
        }
    }

    int offset = 0;
    private void BottomCollision(GameObject origin,  GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);
        target.setY((int)origin.getBounds().getMaxY() +offset);
    }
    private void LeftCollision(GameObject origin, GameObject target) {
        Rectangle targetbounds = target.getBounds();
        CollisionTools.ExchangeHMassVelocity(origin, target);

        target.setX( origin.getX()-  targetbounds.width- offset);
    }

    private void TopCollision( GameObject origin,GameObject target) {
        CollisionTools.ExchangeVMassVelocity(origin, target);
        target.setY(origin.getY()- target.getBounds().height-offset);
    }

    private void RightCollision(GameObject origin, GameObject target) {
        CollisionTools.ExchangeHMassVelocity(origin, target);
        target.setX((int)origin.getBounds().getMaxX()+offset);
    }



}

