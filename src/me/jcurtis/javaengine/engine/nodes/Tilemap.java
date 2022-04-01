package me.jcurtis.javaengine.engine.nodes;

import me.jcurtis.javaengine.engine.utils.Vector2;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Tilemap extends Node {
    public Tilemap(int width, int height) {
        super(NodeType.TILEMAP);
    }
}
