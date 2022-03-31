package me.jcurtis.javaengine;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.nodes.Sprite;
import me.jcurtis.javaengine.window.Window;

public class Main extends JavaEngine {
    static JavaEngine engine = new JavaEngine();

    public static void main(String[] args) {
        engine.initialize();
        engine.initializeWindow(new Window("Test", false, 500, 500));

        Sprite sprite = new Sprite("images/player.png");
    }
}
