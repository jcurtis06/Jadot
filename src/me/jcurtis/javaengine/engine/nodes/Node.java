package me.jcurtis.javaengine.engine.nodes;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.utils.Vector2;

import java.util.ArrayList;

public class Node {
    private final ArrayList<Node> children = new ArrayList<>();

    public Node parent;
    protected NodeType type;
    public Vector2 pos;
    public String name;

    public Node(NodeType nodeType) {
        onTreeEnter();

        this.type = nodeType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addChild(Node node) {
        System.out.println("Added child " + node);
        node.setParent(this);
        children.add(node);
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node node) {
        this.parent = node;
    }

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public void removeChild(Node node) {
        children.remove(node);
    }

    public void removeChild(int index) {
        children.remove(index);
    }

    public void setPos(Vector2 vector2) {
        this.pos = vector2;
    }

    public Vector2 getPos() {
        return this.pos;
    }

    public Node getNode() {
        return this;
    }

    public NodeType getType() {
        return this.type;
    }

    public void onTreeEnter() {
        JavaEngine.registerNode(this);
        System.out.println("Registered a node");

        if (getParent() != null) {
            if (getParent().getPos() != getPos()) {
                pos = getParent().getPos();
            }
        } else {
            pos = new Vector2(0, 0);
        }
    }

    public void update() {
        if (getParent() != null) {
            if (getParent().getPos() != getPos()) {
                pos = getParent().getPos();
            }
        }
    }
}
