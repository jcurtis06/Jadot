package me.jcurtis.javaengine.engine.nodes;

import java.util.ArrayList;

import me.jcurtis.javaengine.engine.utils.Vector2;

public class KinematicBody2D extends Node {
    private ArrayList<CollisionRect2D> collisionRect2Ds = new ArrayList<>();
    private boolean colliding = false;

    public KinematicBody2D() {
        super(NodeType.KINEMATICBODY2D);
        if (collisionRect2Ds == null) System.out.println("WARNING: KinematicBody2D doesn't have a CollisionRect2D!");
    }

    private void applyVelocity(Vector2 velocity) {
        if (velocity.x > 0) {
            // right
            for (int i = 0; i <= velocity.getX(); i++) {
                if (checkCollisions(new Vector2(this.pos.x + 1, this.pos.y))) return;
                
                this.pos.x += 1;
            }
        }
        
        if (velocity.x < 0) {
            // left
            for (int i = 0; i <= Math.abs(velocity.getX()); i++) {
                if (checkCollisions(new Vector2(this.pos.x - 1, this.pos.y))) return;

                this.pos.x -= 1;
            }
        }

        if (velocity.y > 0) {
            // down
            for (int i = 0; i <= Math.abs(velocity.getY()); i++) {
                if (checkCollisions(new Vector2(this.pos.x, this.pos.y + 1))) return;

                this.pos.y += 1;
            }
        } 
        
        if (velocity.y < 0) {
            // up
            for (int i = 0; i <= Math.abs(velocity.getY()); i++) {
                if (checkCollisions(new Vector2(this.pos.x, this.pos.y - 1))) return;

                this.pos.y -= 1;
            }
        }
    }

    private boolean checkCollisions(Vector2 newPos) {
        for (CollisionRect2D myCollider : collisionRect2Ds) {
            if (myCollider.checkCollisionsAt(newPos)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void move(Vector2 velocity) {
        applyVelocity(velocity);
    }

    public void moveAndSlide(Vector2 velocity) {
        applyVelocity(new Vector2(velocity.x, 0));
        applyVelocity(new Vector2(0, velocity.y));
    }

    @Override
    public void addChild(Node node) {
        super.addChild(node);
        if (node.getType().equals(NodeType.COLLISIONRECT)) {
            collisionRect2Ds.add((CollisionRect2D) node);
        }
    }
}
