package me.jcurtis.javaengine.engine.nodes;

import java.util.ArrayList;
import java.awt.Rectangle;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.utils.Vector2;

public class CollisionRect2D extends Node {
    public int width = 0;
    public int height = 0;
    public CollisionRect2D collided;

    public CollisionRect2D(int width, int height) {
        super(NodeType.COLLISIONRECT);

        this.width = width;
        this.height = height;
    }

    public boolean checkCollisions() {
        for (CollisionRect2D other : JavaEngine.colliders) {
            if (other == this) continue;
            if (this.getBounds().intersects(other.getBounds())) {
                this.collided = other;
                return true;
            }
        }
        return false;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.pos.getX(), this.pos.getY(), this.width, this.height);
    }

    public CollisionRect2D getCollided() {
        return this.collided;
    }
}
