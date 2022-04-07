package me.jcurtis.javaengine.engine.nodes.tilemap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;
import me.jcurtis.javaengine.engine.nodes.collisions.ColliderType;
import me.jcurtis.javaengine.engine.nodes.collisions.CollisionShape2D;
import me.jcurtis.javaengine.engine.nodes.collisions.StaticBody2D;
import me.jcurtis.javaengine.engine.utils.Vector2;

public class Tilemap extends Node {
    private int[][] map;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private int cellSize = 0;

    public Tilemap() {
        super(NodeType.TILEMAP);
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int[][] getMap() {
        return map;
    }

    public void addTiles(Tile... tile) {
        Collections.addAll(this.tiles, tile);
    }

    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }

    public void setCellSize(int size) {
        this.cellSize = size;
    }

    public int getCellSize() {
        return this.cellSize;
    }

    public void generateTiles() {
        if (map == null || cellSize == 0 || tiles.isEmpty()) {
            System.out.println("TileMap is missing required parameters");
            System.out.println("*did you remember to setCellSize(int size), setTiles(ArrayList<String> resources), and setMap(int[][] map)?");
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Cell cell = new Cell(tiles.get(map[i][j]), map[i][j], cellSize);
                cell.setOffset(new Vector2(j * cellSize, i * cellSize));
                addChild(cell);
            }
        }
    }
}
