package com.tutorial.game;

import com.tutorial.game.Collisions.CircleToRectCollisionHandler;
import com.tutorial.game.Collisions.SmartRectCollisionHandler;
import com.tutorial.game.GameObjects.CircleObject;

import java.awt.*;
import java.util.LinkedList;

public class Kinematic {
    private LinkedList<GameObject> objects;
    private int worldWidth;
    private int worldHeight;

    public Kinematic(LinkedList<GameObject> objects, int worldWith, int worldHeight){
        this.objects = objects;
        this.worldWidth = worldWith;
        this.worldHeight = worldHeight;
    }
    private SmartRectCollisionHandler rectsCollider = new SmartRectCollisionHandler();
    private CircleToRectCollisionHandler circleToRectCollisionHandler = new CircleToRectCollisionHandler();

    public void tick(){

        for(int i = 0; i<objects.size();i++) {


            GameObject origin = objects.get(i);
            if (origin == null)
                continue;

            origin.x += origin.velX;
            origin.y += origin.velY;

            CorrectAreaBounds(origin, origin.getBounds());
        }


        for(int i = 0; i<objects.size();i++) {

            GameObject origin = objects.get(i);
            if (origin == null)
                continue;
            Rectangle originBounds = origin.getBounds();


            for (int j = i + 1; j < objects.size(); j++) {
                GameObject target = objects.get(j);
                if (target == null)
                    continue;
                if (target.getBounds().intersects(originBounds)) {
                    if(origin instanceof CircleObject){
                        if(target instanceof  CircleObject)
                            ((CircleObject) origin).Collide((CircleObject)target);
                        else
                            circleToRectCollisionHandler.Collide((CircleObject)origin, target);
                    }
                    else if(target instanceof  CircleObject){
                        circleToRectCollisionHandler.Collide((CircleObject)target, origin);
                    }
                    else
                        rectsCollider.Collide(origin, target);
                }
            }
        }
    }


    private void CorrectAreaBounds(GameObject origin, Rectangle originBounds) {
        if (origin.y <= 0 && origin.velY<0) {
            origin.velY = -origin.velY;
            origin.y = 0;
        } else if (origin.y > worldHeight - originBounds.height && origin.velY>0) {
            origin.velY = -origin.velY;
            origin.y = worldHeight - originBounds.height;
        }
        if (origin.x <= 0) {
            origin.velX = -origin.velX;
            origin.x = 0;
        }

        else if (origin.x > worldWidth - originBounds.width) {
            origin.velX = -origin.velX;
            origin.x = worldWidth - originBounds.width;
        }
    }



}
