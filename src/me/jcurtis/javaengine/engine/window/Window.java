package me.jcurtis.javaengine.engine.window;

import javax.swing.*;
import me.jcurtis.javaengine.engine.JavaEngine;
import me.jcurtis.javaengine.engine.input.Input;
import me.jcurtis.javaengine.engine.utils.Vector2;


public class Window {
    String title;
    boolean resizeable;

    Vector2 scale = new Vector2(1, 1);

    int width;
    int height;

    public Viewport viewport;

    public Window(String title, boolean resizeable, int width, int height) {
        this.title = title;
        this.resizeable = resizeable;

        this.width = width;
        this.height = height;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public JFrame init(JavaEngine engine) {
        JFrame window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(this.width, this.height);
        window.setResizable(this.resizeable);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        this.viewport = new Viewport(engine);
        this.viewport.setScale(scale);
        window.add(this.viewport);
        window.addKeyListener(new Input());

        return window;
    }
}
