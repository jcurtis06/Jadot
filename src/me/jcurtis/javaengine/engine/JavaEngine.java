package me.jcurtis.javaengine.engine;

import me.jcurtis.javaengine.nodes.Node;
import me.jcurtis.javaengine.window.Window;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;

public class JavaEngine {
    private static final ArrayList<Node> nodes = new ArrayList<>();

    public static void registerNode(Node node) {
        nodes.add(node);
    }

    public void initialize() {
        System.out.println("Game Started");
    }

    public void initializeWindow(@NotNull Window window) {
        window.init();
    }
}
