package com.piu.engine;

public class CollideLineResult{
    public CollideLineResult(GameObject source, double distance)
    {

        this.Object = source;
        this.Distance = distance;
    }
    public GameObject Object;
    public double Distance;
}
