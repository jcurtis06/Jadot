package me.jcurtis.javaengine.engine.nodes.noderesources;

public class SpriteFrames {
    public String[] images;
    public String name;
    public int fps;

    public SpriteFrames(String name, int fps, String... images) {
        this.images = images;
        this.name = name;
        this.fps = fps;
    }
}
