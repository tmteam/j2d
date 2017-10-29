package com.tutorial.game;

import com.tutorial.game.Collisions.CircleToRectCollisionHandler;
import com.tutorial.game.Collisions.SmartRectCollisionHandler;
import com.tutorial.game.GameObjects.CircleObject;
import com.tutorial.game.GameObjects.Wall;

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

    //int tick = 0;
    public void tick(){
      //  tick++;

        for(int i = 0; i<objects.size();i++) {


            GameObject origin = objects.get(i);
            if (origin == null)
                continue;

            origin.x += origin.velX;
            origin.y += origin.velY;
           // if(tick>=10)
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

    public CollideLineResult collideLine(GameObject except, float x1, float y1, float angle, float lenght){
        CollideLineResult results = null;
        float x2 = x1+ lenght*(float) Math.cos(angle);
        float y2 = y1- lenght*(float) Math.sin(angle);

        for(int i = 0; i<objects.size();i++) {
            GameObject origin = objects.get(i);
            if (origin == null || origin == except)
                continue;
            Point intersection =  origin.getFirstIntersectionWith(x1,y1,x2,y2);
            if(intersection!=null){
                results = new CollideLineResult(origin,intersection.distance(x1,y1));
                x2 = intersection.x;
                y2 = intersection.y;
            }
        }
        return results;
    }

    private void CorrectAreaBounds(GameObject origin, Rectangle originBounds) {
        if(origin instanceof Wall)
            return;
        int offset = -20;
        if (origin.y <= -offset && origin.velY<0) {
            origin.velY = -origin.velY;
            origin.y = -offset;
        } else if (origin.y > worldHeight + offset - originBounds.height && origin.velY>0) {
            origin.velY = -origin.velY;
            origin.y = worldHeight+offset - originBounds.height;
        }
        if (origin.x <= -offset) {
            origin.velX = -origin.velX;
            origin.x = -offset;
        }

        else if (origin.x > worldWidth +offset - originBounds.width) {
            origin.velX = -origin.velX;
            origin.x = worldWidth +offset - originBounds.width;
        }
    }
}
