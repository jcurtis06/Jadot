package me.jcurtis.javaengine;

import me.jcurtis.javaengine.engine.input.Input;
import me.jcurtis.javaengine.engine.nodes.KinematicBody2D;
import me.jcurtis.javaengine.engine.utils.Vector2;

import java.awt.event.*;

public class Player extends KinematicBody2D {
    final int speed = 100;

    public Player() {
        System.out.println("CREATED PLAYER");
    }

    @Override
    public void update() {
        super.update();

        if (Input.isKeyPressed(KeyEvent.VK_UP)) {
            applyVelocity(new Vector2(0, -1));
        }
        if (Input.isKeyPressed(KeyEvent.VK_LEFT)) {
            applyVelocity(new Vector2(-1, 0));
        }
        if (Input.isKeyPressed(KeyEvent.VK_RIGHT)) {
            applyVelocity(new Vector2(1, 0));
        }
        if (Input.isKeyPressed(KeyEvent.VK_DOWN)) {
            applyVelocity(new Vector2(0, 1));
        }
    }
}
