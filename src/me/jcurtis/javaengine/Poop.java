package me.jcurtis.javaengine;

import me.jcurtis.javaengine.engine.nodes.CollisionRect2D;
import me.jcurtis.javaengine.engine.nodes.KinematicBody2D;
import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.Sprite;

public class Poop extends KinematicBody2D {
    @Override
    public void onTreeEnter() {
        super.onTreeEnter();

        CollisionRect2D cr2d = new CollisionRect2D(16, 16);
        cr2d.setName("Poop");

        this.addChild(cr2d);
        this.addChild(new Sprite("images/grass1.png"));
    }
}
