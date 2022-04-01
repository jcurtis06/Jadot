package me.jcurtis.javaengine;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.nodes.CollisionRect2D;
import me.jcurtis.javaengine.engine.nodes.Root;
import me.jcurtis.javaengine.engine.nodes.Sprite;
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

        player.addChild(collisionRect2D);

        ArrayList<CollisionRect2D> cr2ds = new ArrayList<>();

        CollisionRect2D object = new CollisionRect2D(16, 16);
        object.setName("test object");
        object.setPos(new Vector2(100, 100));
        object.setOffset(new Vector2(0, 0));
        object.addChild(new Sprite("images/player.png"));
        
        for (int i = 0; i < 20; i++) {
            CollisionRect2D obj = new CollisionRect2D(16, 16);
            obj.setName("test object");
            obj.setPos(new Vector2(i*20, 100));
            obj.setOffset(new Vector2(0, 0));
            obj.addChild(new Sprite("images/player.png"));

            root.addChild(obj);

            cr2ds.add(obj);
        }
        
        root.addChild(player);
        root.addChild(object);

        Vector2 testpos = new Vector2(200, 200);
        Vector2 testOff = new Vector2(50, 50);

        System.out.println(testpos = testpos.addVec(testOff));

        engine.initialize();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                engine.initializeWindow(new Window("Test", false, 500, 500));
            }
        });
    }
}
