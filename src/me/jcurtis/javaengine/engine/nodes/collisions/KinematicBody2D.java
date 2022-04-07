package me.jcurtis.javaengine.engine.nodes.collisions;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;
import me.jcurtis.javaengine.engine.utils.Direction;
import me.jcurtis.javaengine.engine.utils.Vector2;

import java.awt.Rectangle;

public class KinematicBody2D extends Body {
    private boolean collidingX = false;
    private boolean collidingY = false;
    public Area2D collidedArea = null;

    public Direction collidedDir = Direction.NONE;
    public boolean isOnFloor = false;
    public Vector2 velocity = new Vector2(0, 0);

    public boolean enabled = true;

    /**
     * A node that can interact with collisions. Often used for player characters.
     */
    public KinematicBody2D() {
        super(NodeType.KINEMATICBODY2D);
    }

    private void applyVelocity(Vector2 velocity, Direction upwards) {
        this.velocity = velocity;
        if (velocity.x > 0) {
            // right
            for (int i = 0; i <= velocity.getX(); i++) {
                if (checkCollisions(new Vector2(this.pos.x + 1, this.pos.y))) {
                    collidingX = true;
                    collidedDir = Direction.RIGHT;
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
                    collidedDir = Direction.LEFT;
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

    public void move(Vector2 velocity, Direction up) {
        applyVelocity(velocity, up);
    }
    
    public Vector2 moveAndSlide(Vector2 velocity, Direction up) {
        applyVelocity(new Vector2(velocity.x, 0), up);
        applyVelocity(new Vector2(0, velocity.y), up);

        if (collidingX) return new Vector2(0, velocity.y);
        else if (collidingY) return new Vector2(velocity.x, 0);
        else return velocity;
    }

    private boolean checkCollisions(Vector2 newPos) {
        if (!enabled) return false;
        if (getCollider() == null) {
            return false;
        }
        Body cs2d = checkCollisionsAt(newPos);
        if (cs2d != null) {
            return true;
        }
        return false;
    }

    private Body checkCollisionsAt(Vector2 newPos) {
        int x2 = newPos.getX(), y2 = newPos.getY();
        Rectangle future = new Rectangle(x2, y2, getCollider().width, getCollider().height);

        for (Body c : JavaEngine.colliders) {
            if (c == this) continue;
            if (c.getCollider().getBounds().intersects(future)) {
                if (!this.getMask().contains(c.getLayer())) continue;
                if (c.getType().equals(NodeType.AREA2D)) {
                    collidedArea = (Area2D) c;
                    onArea2DEntered();
                    continue;
                }
                return c;
            }
        }
        return null;
    }

    @Override
    public void addChild(Node node) {
        super.addChild(node);
        if (node.getType().equals(NodeType.COLLISIONSHAPE2D)) {
            setCollider((CollisionShape2D) node);
        }
    }

    public void onArea2DEntered() {

    }
}
