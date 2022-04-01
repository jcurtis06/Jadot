package me.jcurtis.javaengine;

import me.jcurtis.javaengine.engine.input.Input;
import me.jcurtis.javaengine.engine.nodes.Camera2D;

import java.awt.event.KeyEvent;

public class CameraController extends Camera2D {
    public CameraController() {
        super(500, 500);
    }

    @Override
    public void update(){
        super.update();

        if (Input.isKeyPressed(KeyEvent.VK_W)) {
            this.pos.y -= 1;
        }
    }
}
