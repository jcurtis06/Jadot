package me.jcurtis.javaengine.engine.nodes;

import me.jcurtis.javaengine.engine.JavaEngine;

public class CollisionRect2D extends Node {
    public int width = 0;
    public int height = 0;

    public CollisionRect2D(int width, int height) {
        super(NodeType.COLLISIONRECT);

        this.width = width;
        this.height = height;
    }

    public boolean checkCollisions() {
        for (CollisionRect2D other : JavaEngine.colliders) {
            if (other == this) continue;

            if (pos.x < other.pos.x + other.width &&
                pos.x + width > other.pos.x &&
                pos.y < other.pos.y + other.height &&
                pos.y + height > other.pos.y
            ) {
                collided();
                return true;
            }
        }
        return false;
    }

    public void collided() {
        
    }
}
