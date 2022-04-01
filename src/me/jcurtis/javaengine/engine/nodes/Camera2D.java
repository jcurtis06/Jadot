package me.jcurtis.javaengine.engine.nodes;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.utils.Vector2;

public class Camera2D extends Node {
    public int width;
    public int height;

    public Vector2 scale = new Vector2(1, 1);

    public Camera2D(int width, int height) {
        super(NodeType.CAMERA2D);
        this.width = width;
        this.height = height;
    }

    public void setScale(Vector2 vector2) {
        this.scale = vector2;
    }

    @Override
    public void update() {
        super.update();

        for (Node node : JavaEngine.nodes) {
            if (node.getType().equals(NodeType.SPRITE)) {
                Sprite other = (Sprite) node;

                if (overlaps(other)) {
                    other.setPos(other.getPos().subVec(this.getPos()));
                }
            }
        }
    }

    private boolean overlaps(Sprite other) {
        return !(other.getPos().x > this.getPos().x + this.width ||
                other.getPos().x + other.width < this.getPos().x ||
                other.getPos().y > this.getPos().y + this.height ||
                other.getPos().y + other.height < this.getPos().y
                );
    }
}
