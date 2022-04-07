package me.jcurtis.javaengine.engine.nodes.tilemap;

import java.util.ArrayList;
import java.util.Arrays;

import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;
import me.jcurtis.javaengine.engine.nodes.collisions.ColliderType;
import me.jcurtis.javaengine.engine.nodes.collisions.CollisionShape2D;
import me.jcurtis.javaengine.engine.nodes.collisions.StaticBody2D;
import me.jcurtis.javaengine.engine.utils.Vector2;

public class Tilemap extends Node {
    private int[][] map;
    private ArrayList<String> res = new ArrayList<>();
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

    public void addTiles(String... resource) {
        for (String s : resource) {
            this.res.add(s);
        }
    }

    public ArrayList<String> getTiles() {
        return this.res;
    }

    public void setCellSize(int size) {
        this.cellSize = size;
    }

    public int getCellSize() {
        return this.cellSize;
    }

    public void generateTiles() {
        if (map == null || cellSize == 0 || res.isEmpty()) {
            System.out.println("TileMap is missing required parameters");
            System.out.println("*did you remember to setCellSize(int size), setTiles(ArrayList<String> resources), and setMap(int[][] map)?");
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Cell cell = new Cell(res.get(map[i][j]));
                cell.setOffset(new Vector2(j * cellSize, i * cellSize));
                addChild(cell);
                
                if ((i>0 && j>0 && i<map.length-1 && j<map[0].length-1 && map[i-1][j-1]+map[i][j-1]+map[i+1][j-1]+map[i-1][j]+map[i+1][j]+map[i-1][j+1]+map[i][j+1]+map[i+1][j+1] > 8) || map[i][j] == 0) {
                    continue;
                }
                
                StaticBody2D collision = new StaticBody2D();
                collision.setLayer(0);
                collision.addChild(new CollisionShape2D(ColliderType.RECTANGLE, cellSize, cellSize));

                cell.addChild(collision);
            }
        }
    }
}
