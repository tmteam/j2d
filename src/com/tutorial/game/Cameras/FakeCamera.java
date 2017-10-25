package com.tutorial.game.Cameras;

public class FakeCamera implements ICamera{
    @Override
    public float getxOffset() {
        return 0;
    }

    @Override
    public float getyOffset() {
        return 0;
    }

    @Override
    public float getScale() {
        return 1;
    }
}
