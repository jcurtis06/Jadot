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
    public Vector2 offset;

    public Node(NodeType nodeType) {
        this.offset = new Vector2(0, 0);
        this.pos = new Vector2(0, 0);

        this.type = nodeType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addChild(Node node) {
        node.setParent(this);
        children.add(node);
        node.onTreeEnter();
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

    public void queueFree() {
        this.getParent().removeChild(this);
    }

    public void removeChild(Node node) {
        if (!node.getChildren().isEmpty()) {
            for (Node n : node.getChildren()) {
                children.remove(n);
                JavaEngine.nodesForRemoval.add(n);
            }
        }
        
        children.remove(node);
        JavaEngine.nodesForRemoval.add(node);
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

    public void setOffset(Vector2 vector2) {
        this.offset = vector2;
    }

    public Vector2 getOffset() {
        return this.offset;
    }

    public Node getNode() {
        return this;
    }

    public NodeType getType() {
        return this.type;
    }

    public Root getTreeRoot() {
        Node temp = getParent();

        while (temp.getParent() != null) {
            temp = temp.getParent();
        }

        return (Root) temp;
    }

    public void onTreeEnter() {
        JavaEngine.registerNode(this);
    }

    public void update() {
        if (getParent() != null && getParent().getType() != NodeType.ROOT) {
            this.setPos(getParent().getPos().addVec(offset));
        }
    }

    public Vector2 getScreenPos() {
        return null;
    }

    public void ready() {

    }
}
