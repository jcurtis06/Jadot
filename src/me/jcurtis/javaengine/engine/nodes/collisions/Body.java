package me.jcurtis.javaengine.engine.nodes.collisions;

import java.util.ArrayList;

import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;

public class Body extends Node {
    private ArrayList<Integer> mask = new ArrayList<>();
    private int layer;
    private CollisionShape2D collider = null;

    public Body(NodeType nodeType) {
        super(nodeType);
        mask.add(0);
    }

    public void setMask(int... mask) {
        this.mask.remove(0);
        for (int i : mask) {
            this.mask.add(i);
        }
    }

    public void setMask(ArrayList<Integer> mask) {
        this.mask = mask;
    }

    public void setLayer(int index) {
        this.layer = index;
        this.mask.add(index);
    }

    public ArrayList<Integer> getMask() {
        return this.mask;
    }

    public int getLayer() {
        return this.layer;
    }

    public CollisionShape2D getCollider() {
        return collider;
    }

    public void setCollider(CollisionShape2D collider) {
        this.collider = collider;
    }
}
