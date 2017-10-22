package com.tutorial.game.Collisions;

import com.tutorial.game.CircleObject;
import com.tutorial.game.GameObject;
import javafx.scene.shape.Circle;

import java.awt.*;

public class CircleToRectCollisionHandler {
    SmartRectCollisionHandler rectCollisionHandler = new SmartRectCollisionHandler();

    public  void Collide(CircleObject circle, GameObject origin){
        double centerX = circle.getCenterX();
        double centerY = circle.getCenterY();
        Rectangle originBounds = origin.getBounds();
        if(centerX>= originBounds.getX() && centerX<= originBounds.getMaxX()) {
            rectCollisionHandler.Collide(circle, origin);
            return;
        }
        if(centerY>= originBounds.getY() && centerY<= originBounds.getMaxY()) {
            rectCollisionHandler.Collide(circle, origin);
            return;
        }
        //Если почему то круг оказался внутри прямоугольника - воспользуемся стандартным выходом из этой ситуации
        if(originBounds.contains(centerX,centerY)){
            rectCollisionHandler.Collide(circle, origin);
            return;
        }
        //Проверяем столкновения с углами

        //Левый верх
        if(tryCollide(circle, originBounds.getX(),originBounds.getY(),origin.getVelX(),origin.getVelY()))
            return;
        //Правый верх
        if(tryCollide(circle, originBounds.getMaxX(),originBounds.getY(),origin.getVelX(),origin.getVelY()))
            return;
        //Правый низ
        if(tryCollide(circle, originBounds.getMaxX(),originBounds.getMaxY(),origin.getVelX(),origin.getVelY()))
            return;
        //Левый низ
        if(tryCollide(circle, originBounds.getX(),originBounds.getMaxY(),origin.getVelX(),origin.getVelY()))
            return;

    }
    boolean tryCollide(CircleObject circle, double x, double y, double originVelX,double originVelY)
    {
        //нужно найти расстояния до каждого из углов. Если оно больше радиуса, то столкновения нет

        double radius = circle.getRadius();
        double dx =  x-circle.getCenterX();
        double dy = y - circle.getCenterY();

        double dist = dx*dx+ dy*dy;
        if(dist<=radius){
            //Столкновение с левым верхним углом
            collide(circle, x, y, originVelX,originVelY);
            return true;
        }
        return false;
    }
    void  collide(CircleObject circle, double x, double y, double originVelX,double originVelY){
        return;
    }
}
