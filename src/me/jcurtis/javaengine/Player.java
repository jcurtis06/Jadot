package me.jcurtis.javaengine;

import me.jcurtis.javaengine.engine.input.Input;
import me.jcurtis.javaengine.engine.nodes.Camera2D;
import me.jcurtis.javaengine.engine.nodes.CollisionRect2D;
import me.jcurtis.javaengine.engine.nodes.KinematicBody2D;
import me.jcurtis.javaengine.engine.nodes.Sprite;
import me.jcurtis.javaengine.engine.utils.Direction;
import me.jcurtis.javaengine.engine.utils.Vector2;

import java.awt.event.*;

public class Player extends KinematicBody2D {
    final int speed = 2;
    final double gravity = 1;
    final int jumpForce = 10;
    
    private Vector2 vel = new Vector2(0, 0);

    @Override
    public void update() {
        super.update();
        getInput();
        gravity();
        vel = moveAndSlide(vel, Direction.UP);
    }

    public void getInput() {
        vel.x = 0;

        if (Input.isKeyPressed(KeyEvent.VK_RIGHT) || Input.isKeyPressed(KeyEvent.VK_D)) {
            vel.x += 1*speed;
        }
        if (Input.isKeyPressed(KeyEvent.VK_LEFT) || Input.isKeyPressed(KeyEvent.VK_A)) {
            vel.x -= 1*speed;
        }
        if (Input.isKeyPressed(KeyEvent.VK_SPACE)) {
            jump();
        }
    }

    public void jump() {
        if (isOnFloor) {
            vel.y = -jumpForce;
        }
    }

    public void gravity() {
        vel.y += gravity;
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();

        Camera2D playerCamera = new Camera2D(500, 500);
        playerCamera.setOffset(new Vector2(-90, -90));
        this.addChild(playerCamera);

        getTreeRoot().setMainCamera(playerCamera);

        this.addChild(new Sprite("images/player1.png"));

        CollisionRect2D cr2d = new CollisionRect2D(16, 16);
        cr2d.setName("Player");

        this.addChild(cr2d);
    }
}
