package com.tutorial.game.Collisions;

import com.tutorial.game.CircleObject;
import com.tutorial.game.GameObject;

public class CircleToCircleCollisionHandler {
    public void  Collide(CircleObject origin, CircleObject target){
        CollisionTools.tryCollide(origin, origin.getCenterX(), origin.getCenterY(), origin.getRadius(), target);
    }
}
