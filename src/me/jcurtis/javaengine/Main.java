package me.jcurtis.javaengine;

import javax.swing.SwingUtilities;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.nodes.*;
import me.jcurtis.javaengine.engine.utils.Vector2;
import me.jcurtis.javaengine.engine.window.Window;

public class Main extends JavaEngine {
    static JavaEngine engine = new JavaEngine();

    public static void main(String[] args) {
        Root root = new Root();

        Player player = new Player();

        root.addChild(player);

        engine.initialize();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                engine.initializeWindow(new Window("Test", false, 500, 500));
            }
        });
    }
}
