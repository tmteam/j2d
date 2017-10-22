package com.tutorial.game.Collisions;

import com.tutorial.game.CircleObject;

public class CircleToCircleCollisionHandler {
    public void  Collide(CircleObject origin, CircleObject target){
        double ocx = origin.getCenterX();
        double ocy = origin.getCenterY();

        double tcx = target.getCenterX();
        double tcy = target.getCenterY();

        double dx = tcx - ocx;
        double dy = tcy - ocy;
        double distance = Math.sqrt(dx*dx+ dy*dy);
        double delta = distance- origin.getRadius()- target.getRadius();
        if(delta>0)
            return;



        //https://www.youtube.com/watch?v=vHOUHRmD2PY&t=88
        //http://codeforgames.ru/razrabotka-igr-v-action-script-urok-27/
        double cos = dx/distance;
        double sin = dy/distance;




        //Проекции скорости x и Y на линию действия
        double xvel1Prime = origin.getVelX()* cos + origin.getVelY()*sin;
        double xvel2Prime = target.getVelX()* cos + target.getVelY()*sin;

        //проекции скорости x и y на линию перпендикулярную лин действия
        double yvel1Prime = origin.getVelY()* cos -origin.getVelX()*sin;
        double yvel2Prime = target.getVelY()* cos - target.getVelX()*sin;

        double mass1 = origin.getRadius()*origin.getRadius();
        double mass2 = target.getRadius()*target.getRadius();

        //Рассчёт скоростей
        //проекция суммы импульсов
        double P = mass1*xvel1Prime + mass2*xvel2Prime;
        double V = xvel1Prime-xvel2Prime;
        double v2f = (P+V*mass1)/(mass1+mass2);
        double v1f = v2f- xvel1Prime+ xvel2Prime;
        xvel1Prime = v1f;
        xvel2Prime = v2f;

        //проецирование обратно на оси
        origin.setVelX(xvel1Prime*cos- yvel1Prime*sin);
        target.setVelX(xvel2Prime*cos- yvel2Prime*sin);
        origin.setVelY(yvel1Prime*cos+ xvel1Prime*sin);
        target.setVelY(yvel2Prime*cos+ xvel2Prime*sin);


        //Растаскиваем круги, чтобы они больше не пересекались
        //будем изменять только target:
        target.setX (target.getX() + (-delta+1)*cos);
        target.setY (target.getY() + (-delta+1)*sin);


         ocx = origin.getCenterX();
         ocy = origin.getCenterY();

         tcx = target.getCenterX();
         tcy = target.getCenterY();

         dx = tcx - ocx;
         dy = tcy - ocy;
         distance = Math.sqrt(dx*dx+ dy*dy);
         delta =  origin.getRadius()+ target.getRadius() -distance;


      //  target.setX(tcx+10);
       // target.setY(tcy+10);
    }
}
