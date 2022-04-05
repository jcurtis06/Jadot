package me.jcurtis.javaengine;

import javax.swing.Spring;

import me.jcurtis.javaengine.engine.nodes.Sprite;
import me.jcurtis.javaengine.engine.nodes.collisions.Area2D;
import me.jcurtis.javaengine.engine.nodes.collisions.ColliderType;
import me.jcurtis.javaengine.engine.nodes.collisions.CollisionShape2D;
import me.jcurtis.javaengine.engine.nodes.collisions.KinematicBody2D;
import me.jcurtis.javaengine.engine.nodes.noderesources.AI;
import me.jcurtis.javaengine.engine.nodes.noderesources.AIType;
import me.jcurtis.javaengine.engine.utils.Direction;
import me.jcurtis.javaengine.engine.utils.Vector2;

public class Goomba extends KinematicBody2D {
    private AI ai;
    private Sprite sprite;
    private Area2D killBox;
    private Area2D head;
    private CollisionShape2D cs2Shape2d;

    @Override
    public void update() {
        super.update();
        velocity.y += 0.1;
        velocity = moveAndSlide(velocity, Direction.UP);

        if (killBox.onBodyEntered() != null && killBox.onBodyEntered().getName() == "Player") {
            System.out.println("reaeched");
            Player player = (Player) killBox.onBodyEntered();
            if (player.dead == false) player.kill();
        }

        if (head.onBodyEntered() != null && head.onBodyEntered().getName() == "Player") {
            System.out.println("UH OH STANKy");
            kill();
        }
    }

    public void kill() {
        queueFree();
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();

        ai = new AI(AIType.PATROL, 0.1);
        this.addChild(ai);

        sprite = new Sprite("images/goomba/goomba.png");
        this.addChild(sprite);

        killBox = new Area2D();
        killBox.setName("GoombaHitBox");
        killBox.addChild(new CollisionShape2D(ColliderType.RECTANGLE, 16, 15));
        killBox.setLayer(3);
        killBox.setMask(1);
        this.addChild(killBox);

        head = new Area2D();
        head.setName("GoombaHead");
        head.addChild(new CollisionShape2D(ColliderType.RECTANGLE, 16, 17));
        head.setOffset(new Vector2(0, -16));
        head.setLayer(4);
        head.setMask(1);
        this.addChild(head);

        CollisionShape2D cs2d = new CollisionShape2D(ColliderType.RECTANGLE, 16, 16);
        this.addChild(cs2d);
        this.setLayer(2);
        this.setMask(0);
        this.setName("Goomba");
    }
}
