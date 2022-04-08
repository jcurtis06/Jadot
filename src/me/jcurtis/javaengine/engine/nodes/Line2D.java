package me.jcurtis.javaengine.engine.nodes;

import me.jcurtis.javaengine.engine.utils.Vector2;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Line2D extends Node {
    Vector2 dest;

    public Line2D(Vector2 dest) {
        super(NodeType.LINE2D);
        this.dest = dest;
    }

    public void setDest(Vector2 dest) {
        this.dest = dest;
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.setColor(new Color(100, 100, 100));
        g.drawLine(pos.getX() - getTreeRoot().getMainCamera().getPos().getX(), pos.getY() - getTreeRoot().getMainCamera().getPos().getY(), dest.getX() - getTreeRoot().getMainCamera().getPos().getX(), dest.getY() - getTreeRoot().getMainCamera().getPos().getY());
    }
}
