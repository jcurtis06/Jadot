package me.jcurtis.javaengine.engine;

import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.window.Window;

import java.util.ArrayList;

public class JavaEngine {
    public Window window;

    public static final ArrayList<Node> nodes = new ArrayList<>();

    public static void registerNode(Node node) {
        nodes.add(node);
    }

    public void initialize() {
        System.out.println("Game Started");
    }

    public void initializeWindow(Window window) {
        window.init(this);
    }

    public void update() {
        for (Node node : nodes) {
            node.update();
        }
    }
}
