package me.jcurtis.javaengine.engine.nodes;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform;

public class Sprite extends Node {
    private BufferedImage sprite;

    public int width;
    public int height;
    public boolean flipped = false;

    public Sprite(String resPath) {
        super(NodeType.SPRITE);
        if (resPath == null) return;
        loadImage(resPath);
    }

    public void loadImage(String resPath) {
        try {
            sprite = ImageIO.read(new File(resPath));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        if (!flipped) {
            g.drawImage(
                sprite,
                pos.getX() - getTreeRoot().getMainCamera().getPos().getX(),
                pos.getY() - getTreeRoot().getMainCamera().getPos().getY(),
                observer
            );
        } else {
            g.drawImage(
                sprite,
                pos.getX() - getTreeRoot().getMainCamera().getPos().getX() + sprite.getWidth(),
                pos.getY() - getTreeRoot().getMainCamera().getPos().getY(),
                -sprite.getWidth(),
                sprite.getHeight(),
                observer
            );
        }
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();
    }
}
