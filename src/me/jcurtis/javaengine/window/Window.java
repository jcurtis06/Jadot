package me.jcurtis.javaengine.window;

import javax.swing.*;

public class Window {
    String title;
    boolean resizeable;

    int width;
    int height;

    public Window(String title, boolean resizeable, int width, int height) {
        this.title = title;
        this.resizeable = resizeable;

        this.width = width;
        this.height = height;
    }

    public JFrame init() {
        JFrame window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(500, 500);
        window.setResizable(this.resizeable);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        return window;
    }
}
