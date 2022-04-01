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
        player.setPos(new Vector2(200, 200));
        player.setOffset(new Vector2(0, 0));
        player.addChild(new Sprite("images/player.png"));
        CollisionRect2D collisionRect2D = new CollisionRect2D(16, 16);
        collisionRect2D.setPos(new Vector2(10, 10));
        collisionRect2D.setOffset(new Vector2(0, 16));
        player.addChild(collisionRect2D);

        Node testBody = new Node(NodeType.NODE);
        testBody.setPos(new Vector2(50, 50));

        Sprite newSprite = new Sprite("images/player2.png");
        newSprite.setSize(16, 16);
        newSprite.setOffset(new Vector2(0, 0));

        testBody.addChild(newSprite);

        CameraController camera2D = new CameraController();
        camera2D.setPos(new Vector2(0, 0));

        root.addChild(camera2D);
        root.addChild(player);
        root.addChild(testBody);

        engine.initialize();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                engine.initializeWindow(new Window("Test", false, 500, 500));
            }
        });
    }
}
