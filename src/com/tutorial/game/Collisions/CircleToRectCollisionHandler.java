package com.tutorial.game.Collisions;

import com.tutorial.game.GameObjects.CircleObject;
import com.tutorial.game.GameObject;

import java.awt.*;

public class CircleToRectCollisionHandler {
    SmartRectCollisionHandler rectCollisionHandler = new SmartRectCollisionHandler();
    StupidRectCollisionHandler stupidRectCollisionHandler = new StupidRectCollisionHandler();

    public  void Collide(CircleObject circle, GameObject origin){
        double centerX = circle.getCenterX();
        double centerY = circle.getCenterY();
        double collideResultAngle = 0;
        Rectangle originBounds = origin.getBounds();

        if(centerX>= originBounds.getX() && centerX<= originBounds.getMaxX()) {
            collideResultAngle= rectCollisionHandler.Collide(circle, origin);
        }
        else if(centerY>= originBounds.getY() && centerY<= originBounds.getMaxY()) {
            collideResultAngle =  rectCollisionHandler.Collide(circle, origin);
        }
        //Если почему то круг оказался внутри прямоугольника - воспользуемся стандартным выходом из этой ситуации
        else if(originBounds.contains(centerX,centerY)){
            collideResultAngle =  rectCollisionHandler.Collide(circle, origin);
        }
        //Проверяем столкновения с углами

        //Левый верх
        else if(tryCollide(circle, originBounds.getX(),originBounds.getY(),origin))
            collideResultAngle = 0;
        //Правый верх
        else if (tryCollide(circle, originBounds.getMaxX(),originBounds.getY(),origin))
            collideResultAngle = 0;
        //Правый низ
        else if (tryCollide(circle, originBounds.getMaxX(),originBounds.getMaxY(),origin))
            collideResultAngle = 180;

        //Левый низ
       else if(tryCollide(circle, originBounds.getX(),originBounds.getMaxY(),origin))
            collideResultAngle = 180;
        else
            return;

        //Рассчёт угловой скорости


        double k = 0.2; //коэффициент сцепления
        double vCollide = 0;//Скорость столкновения
        if(collideResultAngle>270 || collideResultAngle==0){
            vCollide = circle.getVelX()- origin.getVelX();
        }
        else if(collideResultAngle <= 90){
            vCollide = circle.getVelY()- origin.getVelY();
        }
        else if(collideResultAngle <= 180){
            vCollide = -circle.getVelX()+ origin.getVelX();
        }
        else if(collideResultAngle <=270){
            vCollide = -circle.getVelY()+ origin.getVelY();
        }
        double w = vCollide/circle.getRadius();
        circle.setAngleVelocity(k*w + circle.getAngleVelocity()*(1-k));
    }
    boolean tryCollide(CircleObject circle, double x, double y, GameObject object)
    {
        return CollisionTools.tryCollide(object,x,y,0, circle);
    }


}
