package com.nilgam;

import java.util.List;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import javafx.geometry.Point2D;

public class PlayerComponent extends Component {

    private Point2D previousPosition;

    public void moveLeft() {

        entity.translateX(-5);
        if (isCollidingWithBlocks()) {
            entity.translateX(5);
        }
    }

    public void moveRight() {
        entity.translateX(5);
        if (isCollidingWithBlocks()) {
            entity.translateX(-5);
        }
    }

    public void moveUp() {
        entity.translateY(-5);
        if (isCollidingWithBlocks()) {
            entity.translateY(5);
        }
    }

    public void moveDown() {
        entity.translateY(5);
        if (isCollidingWithBlocks()) {
            entity.translateY(-5);
        }
    }

    public void movePreviousPosition() {

        entity.setPosition(previousPosition.getX(), previousPosition.getY());
    }

    public boolean isCollidingWithBlocks() {
        List<Entity> entities = FXGL.getWorldProperties().getObject("blocks");
        for (Entity ent : entities) {
            if (entity.isColliding(ent)) {
                return true;
            }
        }
        return false;
    }
}
