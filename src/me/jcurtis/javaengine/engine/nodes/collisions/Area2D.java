package me.jcurtis.javaengine.engine.nodes.collisions;

import java.util.ArrayList;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;

public class Area2D extends Body {
    private Body collided = null;

    /**
     * Does not use collisions.
     * Often used as a trigger or in place of a raycast.
     * 
     * Must have a child of type CollisionShape2D.
     */
    public Area2D() {
        super(NodeType.AREA2D);
    }

    @Override
    public void update() {
        super.update();

        for (Body cs2d : JavaEngine.colliders) {
            // check to see if the collider is part of this Area2D
            if (cs2d == this) continue;

            // check if they overlap eachother
            if (cs2d.getCollider().getBounds().intersects(getCollider().getBounds().getBounds())) {
                if (!this.getMask().contains(cs2d.getLayer())) continue;
                // all checks have passed, the other collider is valid
                onBodyEntered(cs2d);
            }
        }
    }

    public void onBodyEntered(Body cs2d) {
        collided = cs2d;
    }

    public Body onBodyEntered() {
        return collided;
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();

        for (Node node : getChildren()) {
            if (node.getType().equals(NodeType.COLLISIONSHAPE2D)) {
                setCollider((CollisionShape2D) node);
            }
        }
    }
}
