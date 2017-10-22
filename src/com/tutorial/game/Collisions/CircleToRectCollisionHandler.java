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
        if(tryCollide(circle, originBounds.getX(),originBounds.getY(),origin))
            return;
        //Правый верх
        if(tryCollide(circle, originBounds.getMaxX(),originBounds.getY(),origin))
            return;
        //Правый низ
        if(tryCollide(circle, originBounds.getMaxX(),originBounds.getMaxY(),origin))
            return;
        //Левый низ
        if(tryCollide(circle, originBounds.getX(),originBounds.getMaxY(),origin));
            return;

    }
    boolean tryCollide(CircleObject circle, double x, double y, GameObject object)
    {
        return CollisionTools.tryCollide(object,x,y,0, circle);
    }


}
