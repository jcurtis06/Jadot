package me.jcurtis.javaengine.engine.nodes;

import java.util.ArrayList;

import me.jcurtis.javaengine.engine.utils.Vector2;

public class KinematicBody2D extends Node {
    private ArrayList<CollisionRect2D> collisionRect2Ds = new ArrayList<>();

    public KinematicBody2D() {
        super(NodeType.KINEMATICBODY2D);

        if (collisionRect2Ds == null) System.out.println("WARNING: KinematicBody2D doesn't have a CollisionRect2D!");
    }

    public void applyVelocity(Vector2 vector2) {
        setPos(this.pos.addVec(vector2));

        if (collisionRect2Ds == null) return;
        for (CollisionRect2D cr2d : collisionRect2Ds) {
            cr2d.update();
            if (cr2d.checkCollisions()) {
                setPos(this.pos.subVec(vector2));
                return;
            }
        }
    }

    @Override
    public void addChild(Node node) {
        super.addChild(node);
        if (node.getType().equals(NodeType.COLLISIONRECT)) {
            collisionRect2Ds.add((CollisionRect2D) node);
        }
    }
}
