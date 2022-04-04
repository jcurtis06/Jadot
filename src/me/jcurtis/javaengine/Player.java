package me.jcurtis.javaengine;

import me.jcurtis.javaengine.engine.input.Input;
import me.jcurtis.javaengine.engine.nodes.Camera2D;
import me.jcurtis.javaengine.engine.nodes.CollisionRect2D;
import me.jcurtis.javaengine.engine.nodes.KinematicBody2D;
import me.jcurtis.javaengine.engine.nodes.Sprite;
import me.jcurtis.javaengine.engine.utils.Vector2;

import java.awt.event.*;

public class Player extends KinematicBody2D {
    final int speed = 1;
    
    private Vector2 vel = new Vector2(0, 0);

    @Override
    public void update() {
        super.update();
        
        getInput();
    }

    public void getInput() {
        vel = new Vector2(0, 0);

        if (Input.isKeyPressed(KeyEvent.VK_RIGHT)) {
            vel.x += 1;
        }
        if (Input.isKeyPressed(KeyEvent.VK_LEFT)) {
            vel.x -= 1;
        }
        if (Input.isKeyPressed(KeyEvent.VK_DOWN)) {
            vel.y += 1;
        }
        if (Input.isKeyPressed(KeyEvent.VK_UP)) {
            vel.y -= 1;
        }

        applyVelocity(vel);
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
