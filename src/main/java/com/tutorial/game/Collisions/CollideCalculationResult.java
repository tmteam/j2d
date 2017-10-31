package com.tutorial.game.Collisions;

import java.awt.*;

public class CollideCalculationResult{
    double targetRelativePreviousX;
    double targetRelativePreviousY;
    private double targetRelativeCurrentX;
    private double targetRelativeCurrentY;

    public CollideCalculationResult(double targetRelativePreviousX,double targetRelativePreviousY,double targetRelativeCurrentX,double targetRelativeCurrentY){

        this.targetRelativePreviousX = targetRelativePreviousX;
        this.targetRelativePreviousY = targetRelativePreviousY;
        this.targetRelativeCurrentX = targetRelativeCurrentX;
        this.targetRelativeCurrentY = targetRelativeCurrentY;

        this.bestSide = ColideSide.noCollision;
        shortestDistance = Double.POSITIVE_INFINITY;
    }

    public void  checkSide(ColideSide side, double startX, double startY, double endX, double endY){
        Point point =  CollisionTools.getIntersectionOrNull(
                targetRelativePreviousX, targetRelativePreviousY,
                targetRelativeCurrentX, targetRelativeCurrentY,
                startX,startY, endX,endY);
        if(point!=null){
            double newsDistance = point.distance(targetRelativePreviousX, targetRelativePreviousY);
            if(newsDistance<shortestDistance)
            {
                shortestDistance = newsDistance;
                this.bestSide = side;
                this.collidePoint = point;
            }
        }
    }
    public Point collidePoint = null;
    public int collideCount;
    public ColideSide bestSide;
    public double shortestDistance;



}
