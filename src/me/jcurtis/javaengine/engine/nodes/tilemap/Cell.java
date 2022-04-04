package me.jcurtis.javaengine.engine.nodes.tilemap;

import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;
import me.jcurtis.javaengine.engine.nodes.Sprite;

public class Cell extends Node {
    String res;

    public Cell(String res) {
        super(NodeType.CELL);
        this.res = res;
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();

        if (this.res == "") return;

        Sprite sprite = new Sprite(res);
        sprite.setSize(16, 16);
        this.addChild(sprite);
    }
}
