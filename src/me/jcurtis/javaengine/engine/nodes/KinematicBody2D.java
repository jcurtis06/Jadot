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
        Vector2 newPos = new Vector2(this.pos.getX() + velocity.x, this.pos.getY() + velocity.y);

        for (CollisionRect2D myCollider : collisionRect2Ds) {
            if (myCollider.checkCollisionsAt(newPos)) {
                colliding = true;
                System.out.println("colliding");
            } else {
                colliding = false;
            }
        }

        if (!colliding) {
            this.setPos(this.pos.addVec(velocity));
        }
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
