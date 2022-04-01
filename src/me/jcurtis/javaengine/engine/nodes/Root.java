package me.jcurtis.javaengine.engine.nodes;

public class Root extends Node {
    public Camera2D mainCam2d;

    public Root() {
        super(NodeType.ROOT);
    }

    public void setMainCamera(Camera2D camera2d) {
        this.mainCam2d = camera2d;
    }

    public Camera2D getMainCamera() {
        return this.mainCam2d;
    }
}
