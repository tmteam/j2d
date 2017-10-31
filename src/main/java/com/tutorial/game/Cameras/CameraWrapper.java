package com.tutorial.game.Cameras;

public class CameraWrapper implements ICamera {

    private ICamera camera = new FakeCamera();
    public CameraWrapper(){}
    public CameraWrapper(ICamera camera){
        this.camera = camera;
    }
    public void setCamera(ICamera camera){
        this.camera = camera;
    }

    @Override
    public float getxOffset() {
        return camera.getxOffset();
    }

    @Override
    public float getyOffset() {
        return camera.getyOffset();
    }

    @Override
    public float getScale() {
        return camera.getScale();
    }
}
