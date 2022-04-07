package me.jcurtis.javaengine.engine.nodes;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.utils.Vector2;
import me.jcurtis.javaengine.engine.window.Window;

public class Camera2D extends Node {
    public int width;
    public int height;

    public Vector2 zoom = new Vector2(1, 1);

    public Camera2D(int width, int height) {
        super(NodeType.CAMERA2D);
        this.width = width;
        this.height = height;
    }

    public void setZoom(Vector2 vector2) {
        JavaEngine.window.viewport.setScale(vector2);
        this.zoom = vector2;
    }

    @Override
    public void update() {
        super.update();
    }
}
