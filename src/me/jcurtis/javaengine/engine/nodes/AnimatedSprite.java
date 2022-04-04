package me.jcurtis.javaengine.engine.nodes;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import me.jcurtis.javaengine.engine.nodes.noderesources.SpriteFrames;

public class AnimatedSprite extends Sprite {
    public ArrayList<SpriteFrames> spriteFrames = new ArrayList<>();
    public SpriteFrames currentAnimation;

    private int frameIndex;
    private boolean started = false;

    public AnimatedSprite(SpriteFrames... animations) {
        super(null);
        for (SpriteFrames sf : animations) {
            spriteFrames.add(sf);
        }
    }

    public void play(String animation) {
        for (SpriteFrames sf : spriteFrames) {
            if (currentAnimation == null || (sf.name == animation && sf.name != currentAnimation.name)) {
                currentAnimation = sf;
                frameIndex = 0;
                if (!started) {
                    startLoop();
                    started = true;
                }
                return;
            }
        }
    }

    public void startLoop() {
        Runnable loopFrames = new Runnable() {
            public void run() {
                loadImage(currentAnimation.images[frameIndex]);
                if (frameIndex + 1 >= currentAnimation.images.length) {
                    frameIndex = 0;
                } else {
                    frameIndex++;
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(loopFrames, 0, currentAnimation.fps, TimeUnit.MILLISECONDS);
    }
}
