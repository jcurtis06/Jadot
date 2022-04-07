package me.jcurtis.javaengine.engine.nodes.tilemap;

import java.util.ArrayList;

public class Tile {
    public String imagePath = "";
    public boolean hasCollisions = false;
    public String name = "air";
    public int mapNum = 0;
    public boolean makeArea = false;
    public ArrayList<Integer> mask = new ArrayList<>();
    public int layer = 0;
}
