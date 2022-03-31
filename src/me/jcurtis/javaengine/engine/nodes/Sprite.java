package me.jcurtis.javaengine.engine.nodes;

import javax.imageio.ImageIO;

import me.jcurtis.javaengine.engine.utils.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Sprite extends Node {
    private BufferedImage sprite;

    public Sprite(String resPath) {
        super(NodeType.SPRITE);
        loadImage(resPath);
    }

    private void loadImage(String resPath) {
        try {
            sprite = ImageIO.read(new File(resPath));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        if (pos == null) {
            if (getParent() != null) {
                pos = getParent().getPos();
                System.out.println("Found parent pos");
                System.out.println(pos.toString());
            } else {
                pos = new Vector2(0, 0);
                System.out.println("Defaulted to 0, 0");
                System.out.println(pos.toString());
            }
        }

        g.drawImage(
                sprite,
                pos.x,
                pos.y,
                observer
        );
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();
        System.out.println("with the type Sprite");
    }
}
