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

public class Goomba extends KinematicBody2D {
    private AI ai;
    private Sprite sprite;
    private Area2D a2d;
    private CollisionShape2D cs2Shape2d;

    @Override
    public void update() {
        super.update();
        velocity.y += 0.1;
        velocity = moveAndSlide(velocity, Direction.UP);

        if (a2d.onBodyEntered() != null && a2d.onBodyEntered().getParent().getName() == "Player") {
            Player player = (Player) a2d.onBodyEntered().getParent();
            player.kill();
        }
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();

        ai = new AI(AIType.PATROL, 0.1);
        this.addChild(ai);

        sprite = new Sprite("images/goomba/goomba.png");
        this.addChild(sprite);

        a2d = new Area2D();
        a2d.setName("GoombaHitBox");
        a2d.addChild(new CollisionShape2D(ColliderType.RECTANGLE, 16, 16));
        a2d.setLayer(3);
        a2d.setMask(1);
        this.addChild(a2d);

        CollisionShape2D cs2d = new CollisionShape2D(ColliderType.RECTANGLE, 16, 16);
        this.addChild(cs2d);
        this.setLayer(2);
        this.setMask(0);
        this.setName("Goomba");
    }
}
