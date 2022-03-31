package me.jcurtis.javaengine.engine.nodes;

import me.jcurtis.javaengine.engine.utils.Vector2;

public class KinematicBody2D extends Node {
    public KinematicBody2D() {
        super(NodeType.KINEMATICBODY2D);
    }

    public void applyVelocity(Vector2 vector2) {
        this.pos.x += vector2.x;
        this.pos.y += vector2.y;
    }
}
