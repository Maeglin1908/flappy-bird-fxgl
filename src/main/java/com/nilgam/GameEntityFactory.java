package com.nilgam;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.PhysicsComponent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameEntityFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityType.PLAYER)
                // .at(300, 300)
                .viewWithBBox(new Rectangle(25, 25, Color.BLUE))
                .with(new PlayerComponent())
                .collidable()
                .build();
    }

    @Spawns("brick")
    public Entity newBrick(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityType.BRICK)
                // .viewWithBBox(new Circle(15, 15, 15, Color.YELLOW))
                .viewWithBBox("brick.png")
                .with(new PhysicsComponent())
                .collidable()
                .build();
    }
}
