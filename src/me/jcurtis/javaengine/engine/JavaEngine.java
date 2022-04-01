package me.jcurtis.javaengine.engine;

import me.jcurtis.javaengine.engine.nodes.Camera2D;
import me.jcurtis.javaengine.engine.nodes.CollisionRect2D;
import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;
import me.jcurtis.javaengine.engine.window.Window;

import java.util.ArrayList;

public class JavaEngine {
    public Window window;

    public static final ArrayList<Node> nodes = new ArrayList<>();
    public static final ArrayList<CollisionRect2D> colliders = new ArrayList<>();
    public static Camera2D mainCamera = null;

    public static void registerNode(Node node) {
        nodes.add(node);
    }

    public void initialize() {
        for (Node n : nodes) {
            if (n.getType().equals(NodeType.COLLISIONRECT)) {
                CollisionRect2D cr = (CollisionRect2D) n;
                colliders.add(cr);
            }

            if (n.getType().equals(NodeType.CAMERA2D)) {
                System.out.println("found main cam");
                mainCamera = (Camera2D) n;
            }
        }
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
