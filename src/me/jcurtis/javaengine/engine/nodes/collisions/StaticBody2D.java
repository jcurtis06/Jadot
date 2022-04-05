package me.jcurtis.javaengine.engine.nodes.collisions;

import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;

public class StaticBody2D extends Body {
    public StaticBody2D() {
        super(NodeType.STATICBODY2D);
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
