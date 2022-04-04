package me.jcurtis.javaengine.engine.nodes;

import java.util.Timer;
import java.util.TimerTask;

public class TimerNode extends Node {
    public Timer timer;
    public int delay;
    public boolean oneShot;
    public boolean autoRestart;
    public boolean startOnLoad;

    public TimerNode(int delay, boolean oneShot, boolean autoRestart, boolean startOnLoad) {
        super(NodeType.TIMER);
        this.timer = new Timer();
        this.delay = delay;
        this.oneShot = oneShot;
        this.autoRestart = autoRestart;
        this.startOnLoad = startOnLoad;
    }

    public void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onTimeout();
            }
        }, delay);
    }

    public void onTimeout() {

    }
}
