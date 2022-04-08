package me.jcurtis.javaengine.engine;

import com.sun.tools.javac.Main;
import me.jcurtis.javaengine.engine.nodes.Camera2D;
import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;
import me.jcurtis.javaengine.engine.nodes.collisions.Body;
import me.jcurtis.javaengine.engine.nodes.collisions.CollisionShape2D;
import me.jcurtis.javaengine.engine.utils.Vector2;
import me.jcurtis.javaengine.engine.window.Window;

import java.util.ArrayList;
import java.util.HashMap;

public class JavaEngine {
    public static Window window;

    public static ArrayList<Node> nodes = new ArrayList<>();
    public static ArrayList<Node> nodesForRemoval = new ArrayList<>();
    public static ArrayList<Body> colliders = new ArrayList<>();
    public static Camera2D mainCamera = null;

    private static HashMap<Node, Vector2> startingPos = new HashMap<>();

    public static void registerNode(Node node) {
        startingPos.put(node, node.getPos());
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
        for (Node n : nodes) {
            n.ready();
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
