package me.jcurtis.javaengine.engine.input;

import java.awt.event.*;
import java.util.ArrayList;

public class Input implements KeyListener {
    private static ArrayList<Integer> pressedKeys = new ArrayList<>();

    public static boolean isKeyPressed(int key) {
        if (pressedKeys.contains(key)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.remove(Integer.valueOf(e.getKeyCode()));
        }
    }
}
