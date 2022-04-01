package me.jcurtis.javaengine.engine.nodes;

import me.jcurtis.javaengine.engine.utils.Vector2;

public class Cell extends Node {
    String res;
    Tilemap tilemap;

    public Cell(String res, Tilemap tilemap) {
        super(NodeType.CELL);

        this.res = res;
        this.tilemap = tilemap;
    }

    public void render() {
        Sprite sprite = new Sprite(this.res);
        sprite.setSize(16, 16);

        sprite.setParent(this);
    }
}
