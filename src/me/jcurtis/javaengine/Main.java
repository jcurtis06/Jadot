package me.jcurtis.javaengine;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.nodes.*;
import me.jcurtis.javaengine.engine.nodes.tilemap.Tilemap;
import me.jcurtis.javaengine.engine.utils.Vector2;
import me.jcurtis.javaengine.engine.window.Window;

public class Main extends JavaEngine {
    static JavaEngine engine = new JavaEngine();

    public static void main(String[] args) {
        Root root = new Root();

        int[][] map =
        {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
        };

        ArrayList<String> tiles = new ArrayList<>();
        tiles.add("");
        tiles.add("images/grass1.png");

        Tilemap world = new Tilemap(16, map, tiles);
        world.generateTiles();

        Player player = new Player();
        player.setPos(new Vector2(0, -32));

        root.addChild(player);
        root.addChild(world);

        engine.initialize();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                engine.initializeWindow(new Window("Test", false, 500, 500));
            }
        });
    }
}
