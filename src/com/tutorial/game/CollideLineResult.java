package com.tutorial.game;

public class CollideLineResult{
    public CollideLineResult(GameObject source, double distance)
    {

        this.Object = source;
        this.Distance = distance;
    }
    public GameObject Object;
    public double Distance;
}
