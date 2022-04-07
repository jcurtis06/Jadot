package me.jcurtis.javaengine.engine;

import me.jcurtis.javaengine.engine.nodes.Camera2D;
import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;
import me.jcurtis.javaengine.engine.nodes.collisions.Body;
import me.jcurtis.javaengine.engine.nodes.collisions.CollisionShape2D;
import me.jcurtis.javaengine.engine.window.Window;

import java.util.ArrayList;

public class JavaEngine {
    public static Window window;

    public static ArrayList<Node> nodes = new ArrayList<>();
    public static ArrayList<Node> nodesForRemoval = new ArrayList<>();
    public static ArrayList<Body> colliders = new ArrayList<>();
    public static Camera2D mainCamera = null;

    public static void registerNode(Node node) {
        nodes.add(node);
    }

    public void initialize() {
        for (Node n : nodes) {
            if (n instanceof Body) {
                Body cr = (Body) n;
                colliders.add(cr);
            }

            if (n.getType().equals(NodeType.CAMERA2D)) {
                mainCamera = (Camera2D) n;
            }
        }
    }

    public void initializeWindow(Window windowObj) {
        window = windowObj;
        window.init(this);
    }

    public void update() {
        if (!nodesForRemoval.isEmpty()) {
            nodes.removeAll(nodesForRemoval);
        }
        for (Node node : nodes) {
            node.update();
        }
    }
}
