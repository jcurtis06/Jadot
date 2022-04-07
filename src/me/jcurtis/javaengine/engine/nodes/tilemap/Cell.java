package me.jcurtis.javaengine.engine.nodes.tilemap;

import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;
import me.jcurtis.javaengine.engine.nodes.Sprite;
import me.jcurtis.javaengine.engine.nodes.collisions.Area2D;
import me.jcurtis.javaengine.engine.nodes.collisions.ColliderType;
import me.jcurtis.javaengine.engine.nodes.collisions.CollisionShape2D;
import me.jcurtis.javaengine.engine.nodes.collisions.StaticBody2D;
import me.jcurtis.javaengine.engine.utils.Vector2;

public class Cell extends Node {
    Tile tile;
    int index;
    int cellSize;

    public Cell(Tile tile, int index, int cellSize) {
        super(NodeType.CELL);
        this.tile = tile;
        this.index = index;
        this.cellSize = cellSize;
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();

        if (this.tile.imagePath.equals("")) return;

        Sprite sprite = new Sprite(this.tile.imagePath);
        sprite.setSize(cellSize, cellSize);
        this.addChild(sprite);

        if (this.tile.hasCollisions) {
            if (!this.tile.makeArea) {
                StaticBody2D collision = new StaticBody2D();
                collision.setLayer(tile.layer);
                if (!tile.mask.isEmpty()) collision.setMask(tile.mask);
                collision.setName("tile_" + this.tile.name);
                collision.addChild(new CollisionShape2D(ColliderType.RECTANGLE, cellSize, cellSize));

                addChild(collision);
            } else {
                Area2D collision = new Area2D();
                collision.setLayer(tile.layer);
                if (!tile.mask.isEmpty()) collision.setMask(tile.mask);
                collision.setName("tile_" + this.tile.name);
                collision.addChild(new CollisionShape2D(ColliderType.RECTANGLE, cellSize, cellSize));

                addChild(collision);
            }
        }
    }
}
