package me.jcurtis.javaengine;

import javax.swing.SwingUtilities;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.nodes.Sprite;
import me.jcurtis.javaengine.engine.utils.Vector2;
import me.jcurtis.javaengine.engine.window.Window;

public class Main extends JavaEngine {
    static JavaEngine engine = new JavaEngine();

    public static void main(String[] args) {
        engine.initialize();
    
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                engine.initializeWindow(new Window("Test", false, 500, 500));
            }
        });

        Player player = new Player();
        player.setPos(new Vector2(200, 200));
        player.addChild(new Sprite("images/player.png"));
        new Sprite("images/player.png");
    }
}
