package com.tutorial.game.Collisions;

import com.tutorial.game.GameObjects.CircleObject;
import com.tutorial.game.GameObject;

import java.awt.*;

public class CollisionTools {
    public static void ExchangeHMassVelocity(GameObject origin, GameObject target){
        //http://optoelectrosys.ru/teor/zakony-soxraneniya-energii-i-impulsa-uprugie-i-neuprugie-stolknoveniya-2.html
        double P =  origin.getVelX()*origin.getMass() + target.getVelX()*target.getMass();
        double massSum = origin.getMass()+ target.getMass();
        double originRes = -origin.getVelX() + 2*P/massSum;
        double targetRes =  -target.getVelX() + 2*P/massSum;
        origin.setVelX(originRes);
        target.setVelX(targetRes);
    }
    public static void ExchangeVMassVelocity(GameObject origin, GameObject target){
        double P =  origin.getVelY()*origin.getMass() + target.getVelY()*target.getMass();
        double massSum = origin.getMass()+ target.getMass();
        double originRes = -origin.getVelY() + 2*P/massSum;
        double targetRes =  -target.getVelY() + 2*P/massSum;
        origin.setVelY(originRes);
        target.setVelY(targetRes);

    }


    public static void ExchangeHVelocity(GameObject origin, GameObject target){
        double v = origin.getVelX();
        origin.setVelX(target.getVelX());
        target.setVelX(v);
    }
    public static void ExchangeVVelocity(GameObject origin, GameObject target){
        double v = origin.getVelY();
        origin.setVelY(target.getVelY());
        target.setVelY(v);
    }

    public static Point getIntersectionOrNull(
            double start1x, double start1y,
            double end1x, double end1y,
            double start2x,double start2y,
            double end2x, double end2y){
        //http://rain.ifmo.ru/cat/view.php/theory/math/geometry-2005
        //https://users.livejournal.com/-winnie/152327.html

        double dir1X = end1x- start1x;
        double dir1Y = end1y-start1y;
        //считаем уравнения прямых проходящих через отрезки
        double a1 = -dir1Y;
        double b1 = +dir1X;
        double d1 = -(a1*start1x + b1*start1y);

        double a2 = -(end2y - start2y);
        double b2 = +(end2x  - start2x);
        double d2 = -(a2*start2x + b2*start2y);

        //подставляем концы отрезков, для выяснения в каких полуплоскотях они
        double seg1_line2_start = a2*start1x + b2*start1y + d2;
        double seg1_line2_end = a2*end1x + b2*end1y + d2;

        double seg2_line1_start = a1*start2x + b1*start2y + d1;
        double seg2_line1_end = a1*end2x + b1*end2y + d1;

        //если концы одного отрезка имеют один знак, значит он в одной полуплоскости и пересечения нет.
        double digit1 = seg1_line2_start * seg1_line2_end;
        double digit2 = seg2_line1_start * seg2_line1_end;

        if (digit1 >= 0 || digit2 >= 0)
            return null;

        double u = seg1_line2_start / (seg1_line2_start - seg1_line2_end);
        return new Point((int)(start1x + u*dir1X), (int) (start1y+ u*dir1Y));
    }

    public static Point getIntersectionOrNull(Point start1,  Point end1,Point start2, Point end2){
        return getIntersectionOrNull(start1.x, start1.y, end1.x, end1.y, start2.x, start2.y, end2.x,end2.y);
    }

    public static boolean  tryCollide(GameObject origin, double originX, double originY, int originRadius, CircleObject target){
        double ocx = originX;
        double ocy = originY;

        double tcx = target.getCenterX();
        double tcy = target.getCenterY();

        double dx = tcx - ocx;
        double dy = tcy - ocy;
        double distance = Math.sqrt(dx*dx+ dy*dy);
        double delta = distance- originRadius- target.getRadius();
        if(delta>0)
            return false;
        //https://www.youtube.com/watch?v=vHOUHRmD2PY&t=88
        //http://codeforgames.ru/razrabotka-igr-v-action-script-urok-27/
        double cos = dx/distance;
        double sin = dy/distance;
        //Проекции скорости x и Y на линию действия
        double xvel1Prime = origin.getVelX()* cos + origin.getVelY()*sin;
        double xvel2Prime = target.getVelX()* cos + target.getVelY()*sin;

        //проекции скорости x и y на линию перпендикулярную лин действия
        double yvel1Prime = origin.getVelY()* cos - origin.getVelX()*sin;
        double yvel2Prime = target.getVelY()* cos - target.getVelX()*sin;

        double mass1 = origin.getMass();
        double mass2 = target.getMass();

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
        target.setX (target.getX() + (-delta)*cos);
        target.setY (target.getY() + (-delta)*sin);
        return true;
    }



    public static boolean  tryCollide(CircleObject origin,CircleObject target){

        double ocx = origin.getCenterX();
        double ocy = origin.getCenterY();

        if(!tryCollide(origin, ocx, ocy, origin.getRadius(), target))
            return false;

        double dx = target.getCenterX() - ocx;
        double dy = target.getCenterY() - ocy;
        double distance = Math.sqrt(dx*dx+ dy*dy);

        double cos = dx/distance;
        double sin = dy/distance;

        //Начинаем рассчёт угловых скоростей.

        //проекции скорости x и y на линию перпендикулярную лин действия
        SaveAngleMomentumTurnCalc(origin, target, cos, sin);
        return true;
    }
    private static void SaveKineticMomentumCalc(CircleObject origin, CircleObject target, double cos, double sin){
        double Vo = origin.getVelY()* cos - origin.getVelX()*sin;
        double Vt = target.getVelY()* cos - target.getVelX()*sin;

        double Rt = target.getRadius();
        double Ro = origin.getRadius();
        double Io = origin.getInertionMoment();
        double It = target.getInertionMoment();
        double At = target.getAngleVelocity();
        double Ao = origin.getAngleVelocity();

        double E = Io*Ao*Ao+ It*At*At;

        double znam = (It*Ro*Ro + Io*Rt*Rt);
        double podKornem = Math.abs(E*znam  - Io*It*(Vo*Vo - 2*Vo*Vt + Vt*Vt));
        double chislit = Ro*Math.sqrt(podKornem) - (Io*Rt*Vo- Io*Rt*Vt);
        double Wt = chislit/znam;
        double Wo = (Wt*Rt-Vt+Vo)/Ro;

        origin.setAngleVelocity(Wo);
        target.setAngleVelocity(Wt);
    }
    private static void SaveAngleMomentumTurnCalc(CircleObject origin, CircleObject target, double cos, double sin) {
        double Vo = origin.getVelY()* cos - origin.getVelX()*sin;
        double Vt = target.getVelY()* cos - target.getVelX()*sin;

        double Rt = target.getRadius();
        double Ro = origin.getRadius();
        double Io = origin.getInertionMoment();
        double It = target.getInertionMoment();

        double At = target.getAngleVelocity();
        double Ao = origin.getAngleVelocity();


        //Исходный полный момент импульса двух тел
        double I = Io*origin.getAngleVelocity()+ It*target.getAngleVelocity();

        double Wt = ( Vt*Io-Vo*Io  + Ro*I)/(Rt*Io + Ro*It);
        //Корректируем
        double ineractionK = 1;
        //Выставляем во второй
        double Wo = (I-It*Wt)/Io;

        Wt = ineractionK*Wt+  target.getAngleVelocity()*(1-ineractionK);

        origin.setAngleVelocity(Wo);
        target.setAngleVelocity(-Wt);
    }

}
