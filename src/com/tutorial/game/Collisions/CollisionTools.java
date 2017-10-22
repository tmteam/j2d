package com.tutorial.game.Collisions;

import com.tutorial.game.GameObject;

import java.awt.*;

public class CollisionTools {
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

        //http://rain.ifmo.ru/cat/view.php/theory/math/geometry-2005
        //https://users.livejournal.com/-winnie/152327.html
/*
        double dir1X = end1.x- start1.x;
        double dir1Y = end1.y-start1.y;
        //считаем уравнения прямых проходящих через отрезки
        double a1 = -dir1Y;
        double b1 = +dir1X;
        double d1 = -(a1*start1.x + b1*start1.y);



        double a2 = -(end2.y - start2.y);
        double b2 = +(end2.x  - start2.y);
        double d2 = -(a2*start2.x + b2*start2.y);


        //подставляем концы отрезков, для выяснения в каких полуплоскотях они
        double seg1_line2_start = a2*start1.x + b2*start1.y + d2;
        double seg1_line2_end = a2*end1.x + b2*end1.y + d2;

        double seg2_line1_start = a1*start2.x + b1*start2.y + d1;
        double seg2_line1_end = a1*end2.x + b1*end2.y + d1;

        //если концы одного отрезка имеют один знак, значит он в одной полуплоскости и пересечения нет.
        double digit1 = seg1_line2_start * seg1_line2_end;
        double digit2 = seg2_line1_start * seg2_line1_end;

        if(digit1==0 || digit2==0)
        {

            return new Point();
            //Есть пограничное пересечение, либо отрезки вытянуты в линию

        }

        if (digit1 > 0 || digit2 > 0)
            return null;

        double u = seg1_line2_start / (seg1_line2_start - seg1_line2_end);
        return new Point((int)(start1.x + u*dir1X), (int) (start1.y+ u*dir1Y));*/
    }
    public static Point substract(Point from, Point target){
        return new Point(from.x-target.x, from.y-target.y);
    }

}
