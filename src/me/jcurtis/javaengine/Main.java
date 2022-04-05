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
            {0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 2, 3, 2, 0, 0, 0, 0, 0, 0, 0,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
        };

        ArrayList<String> tiles = new ArrayList<>();
        tiles.add("");
        tiles.add("images/tiles/block.png");
        tiles.add("images/tiles/brick.png");
        tiles.add("images/tiles/item_block.png");

        Tilemap world = new Tilemap(16, map, tiles);
        world.generateTiles();

        Player player = new Player();
        player.setPos(new Vector2(0, -32));

        Goomba goomba = new Goomba();
        goomba.setPos(new Vector2(16, -32));

        root.addChild(player);
        root.addChild(goomba);
        root.addChild(world);

        engine.initialize();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                engine.initializeWindow(new Window("Test", true, 500, 500));
            }
        });
    }
}
