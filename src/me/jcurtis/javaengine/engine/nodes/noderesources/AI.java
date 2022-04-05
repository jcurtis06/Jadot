package me.jcurtis.javaengine.engine.nodes.noderesources;


import me.jcurtis.javaengine.engine.nodes.Node;
import me.jcurtis.javaengine.engine.nodes.NodeType;
import me.jcurtis.javaengine.engine.nodes.collisions.KinematicBody2D;
import me.jcurtis.javaengine.engine.utils.Direction;

public class AI extends Node {
    public AIType aiType = AIType.INACTIVE;
    public double speed;

    private KinematicBody2D kb2d;
    
    public AI(AIType type, double speed) {
        super(NodeType.AI);

        this.aiType = type;
        this.speed = speed;
    }

    @Override
    public void update() {
        super.update();

        switch (aiType) {
            case PATROL:
                AIPatrol();
                break;
        }
    }

    public void AIPatrol() {
        if (kb2d.collidedDir == Direction.RIGHT) {
            kb2d.velocity.x = -speed;
        } else if (kb2d.collidedDir == Direction.LEFT) {
            kb2d.velocity.x = speed;
        }
    }

    public void AIPatrolInit() {
        kb2d.velocity.x = this.speed;
    }

    public void AIInit() {
        switch (aiType) {
            case PATROL:
                AIPatrolInit();
                break;
        }
    }

    @Override
    public void onTreeEnter() {
        super.onTreeEnter();
        this.kb2d = (KinematicBody2D) getParent();
        AIInit();
    }
}
