package me.jcurtis.javaengine.engine.nodes;

import java.util.ArrayList;

import me.jcurtis.javaengine.engine.utils.Direction;
import me.jcurtis.javaengine.engine.utils.Vector2;

public class KinematicBody2D extends Node {
    private ArrayList<CollisionRect2D> collisionRect2Ds = new ArrayList<>();
    private boolean collidingX = false;
    private boolean collidingY = false;
    
    public boolean isOnFloor = false;

    public KinematicBody2D() {
        super(NodeType.KINEMATICBODY2D);
        if (collisionRect2Ds == null) System.out.println("WARNING: KinematicBody2D doesn't have a CollisionRect2D!");
    }

    private void applyVelocity(Vector2 velocity, Direction upwards) {
        if (velocity.x > 0) {
            // right
            for (int i = 0; i <= velocity.getX(); i++) {
                if (checkCollisions(new Vector2(this.pos.x + 1, this.pos.y))) {
                    collidingX = true;
                    return;
                }
                collidingX = false;
                this.pos.x += 1;
            }
        }
        
        if (velocity.x < 0) {
            // left
            for (int i = 0; i <= Math.abs(velocity.getX()); i++) {
                if (checkCollisions(new Vector2(this.pos.x - 1, this.pos.y))) {
                    collidingX = true;
                    return;
                }
                collidingX = false;
                this.pos.x -= 1;
            }
        }

        if (velocity.y > 0) {
            // down
            for (int i = 0; i <= Math.abs(velocity.getY()); i++) {
                if (checkCollisions(new Vector2(this.pos.x, this.pos.y + 1))) {
                    collidingY = true;
                    if (upwards == Direction.UP) {
                        isOnFloor = true;
                    }
                    return;
                }
                if (upwards == Direction.UP) {
                    isOnFloor = false;
                }
                collidingY = false;
                this.pos.y += 1;
            }
        } 
        
        if (velocity.y < 0) {
            // up
            for (int i = 0; i <= Math.abs(velocity.getY()); i++) {
                if (checkCollisions(new Vector2(this.pos.x, this.pos.y - 1))) {
                    collidingY = true;
                    return;
                }
                if (upwards == Direction.UP) {
                    isOnFloor = false;
                }
                collidingY = false;
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

    public void move(Vector2 velocity, Direction up) {
        applyVelocity(velocity, up);
    }
    /* this needs to return the projected velocity
    if we collide, it should be a vector 2 of 0,0
    if we don't collide, it should just return the inputted velocity

    How...? idk.
    */
    public Vector2 moveAndSlide(Vector2 velocity, Direction up) {
        applyVelocity(new Vector2(velocity.x, 0), up);
        applyVelocity(new Vector2(0, velocity.y), up);

        if (collidingX) return new Vector2(0, velocity.y);
        else if (collidingY) return new Vector2(velocity.x, 0);
        else return velocity;
    }

    @Override
    public void addChild(Node node) {
        super.addChild(node);
        if (node.getType().equals(NodeType.COLLISIONRECT)) {
            collisionRect2Ds.add((CollisionRect2D) node);
        }
    }
}
