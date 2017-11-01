package com.piu.engine.Cameras;

public class ManualCamera implements ICamera {
    private float xOffset = 0;
    private float yOffset = 0;
    private float scale = 1;

    @Override
    public float getxOffset() {
        return xOffset;
    }

    @Override
    public float getyOffset() {
        return yOffset;
    }

    @Override
    public float getScale() {
        return scale;
    }


    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }
}
