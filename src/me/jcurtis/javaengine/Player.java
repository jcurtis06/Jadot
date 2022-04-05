package me.jcurtis.javaengine;

import me.jcurtis.javaengine.engine.input.Input;
import me.jcurtis.javaengine.engine.nodes.AnimatedSprite;
import me.jcurtis.javaengine.engine.nodes.Camera2D;
import me.jcurtis.javaengine.engine.nodes.collisions.ColliderType;
import me.jcurtis.javaengine.engine.nodes.collisions.CollisionShape2D;
import me.jcurtis.javaengine.engine.nodes.collisions.KinematicBody2D;
import me.jcurtis.javaengine.engine.nodes.noderesources.SpriteFrames;
import me.jcurtis.javaengine.engine.utils.Direction;
import me.jcurtis.javaengine.engine.utils.Vector2;

import java.awt.event.*;

public class Player extends KinematicBody2D {
    final double speed = 1;
    final double gravity = 0.1;
    final int jumpForce = 3;
    
    private Vector2 vel = new Vector2(0, 0);
    private AnimatedSprite animatedSprite;
    private boolean dead = false;

    @Override
    public void update() {
        super.update();
        getInput();
        gravity();
        
        vel = moveAndSlide(vel, Direction.UP);

        if (dead) {
            animatedSprite.play("death");
        }
    }

    public void getInput() {
        vel.x = 0;

        if (Input.isKeyPressed(KeyEvent.VK_RIGHT) || Input.isKeyPressed(KeyEvent.VK_D) && !dead) {
            vel.x += 1*speed;
            animatedSprite.flipped = false;
            animatedSprite.play("run");
        } else if (Input.isKeyPressed(KeyEvent.VK_LEFT) || Input.isKeyPressed(KeyEvent.VK_A) && !dead) {
            vel.x -= 1*speed;
            animatedSprite.flipped = true;
            animatedSprite.play("run");
        } else {
            animatedSprite.play("idle");
        }
        if (Input.isKeyPressed(KeyEvent.VK_SPACE) || dead) {
            jump();
        }
    }

    public void jump() {
        if (isOnFloor) {
            vel.y = -jumpForce;
            animatedSprite.play("jump");
        }
    }

    public void gravity() {
        vel.y += gravity;
    }

    public void kill() {
        vel.y = jumpForce;
        dead = true;
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();

        Camera2D playerCamera = new Camera2D(500, 500);
        playerCamera.setOffset(new Vector2(-360/2, -240/2));
        this.addChild(playerCamera);

        getTreeRoot().setMainCamera(playerCamera);

        this.animatedSprite = new AnimatedSprite(new SpriteFrames("death", 100, "images/mario/mario_death.png"), new SpriteFrames("jump", 100, "images/mario/mario_jump.png"), new SpriteFrames("idle", 100, "images/mario/mario_idle.png"), new SpriteFrames("run", 100, "images/mario/mario_run.png", "images/mario/mario_run_1.png", "images/mario/mario_run_2.png"));
        this.addChild(animatedSprite);

        CollisionShape2D cs2d = new CollisionShape2D(ColliderType.RECTANGLE, 16, 16);
        this.addChild(cs2d);

        this.setLayer(1);
        this.setMask(0);
        this.setName("Player");
    }
}
