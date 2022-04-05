package me.jcurtis.javaengine.engine.nodes.collisions;

import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;

import java.awt.Rectangle;

public class CollisionShape2D extends Node {
    public ColliderType colliderType = ColliderType.RECTANGLE;

    public int width = 0;
    public int height = 0;

    public CollisionShape2D(ColliderType colliderType, int width, int height) {
        super(NodeType.COLLISIONSHAPE2D);
        this.colliderType = colliderType;

        this.width = width;
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.pos.getX(), this.pos.getY(), this.width, this.height);
    }
}
