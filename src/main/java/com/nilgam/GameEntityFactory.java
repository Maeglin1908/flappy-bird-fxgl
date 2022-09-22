package com.nilgam;

import java.util.List;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.nilgam.components.BirdComponent;
import com.nilgam.components.PipeComponent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameEntityFactory implements EntityFactory {

    // public GameEntityFactory() {
    // super();
    // }

    @Spawns("bird")
    public Entity newBird(SpawnData data) {
        Entity bird = FXGL.entityBuilder(data)
                .type(EntityType.BIRD)
                .at(FXGL.getWorldProperties().getDouble("birdOffset"), (double) (FXGL.getAppHeight() / 2))
                .viewWithBBox(new Rectangle(
                        FXGL.getWorldProperties().getDouble("birdWidth"),
                        FXGL.getWorldProperties().getDouble("birdHeight"),
                        Color.BLUE))
                .with(new BirdComponent())
                .collidable()
                .build();

        return bird;
    }

    @Spawns("pipetop")
    public Entity newPipeTop(SpawnData data) {
        Entity pipe = FXGL.entityBuilder(data)
                .type(EntityType.PIPE)
                .at(FXGL.getAppWidth() - 100, 0)
                .viewWithBBox(new Rectangle(
                        FXGL.getWorldProperties().getDouble("pipeWidth"),
                        FXGL.random(0,
                                FXGL.getAppHeight() - FXGL.getWorldProperties().getDouble("pipeSpace"))

                ))
                .with(new PipeComponent())
                .with(new OffscreenCleanComponent())
                .collidable()
                .build();

        return pipe;
    }

    @Spawns("pipebottom")
    public Entity newBrick(SpawnData data) {
        List<Entity> pipes = FXGL.getGameWorld().getEntitiesByType(EntityType.PIPE);
        Entity lastPipe = pipes.get(pipes.size() - 1);
        double pipeY = lastPipe.getHeight() + FXGL.getWorldProperties().getDouble("pipeSpace");

        Entity pipe = FXGL.entityBuilder(data)
                .type(EntityType.PIPE)
                .at(FXGL.getAppWidth() - 100, pipeY)
                .viewWithBBox(new Rectangle(
                        FXGL.getWorldProperties().getDouble("pipeWidth"),
                        FXGL.getAppHeight() - pipeY))
                .with(new PipeComponent())
                .with(new OffscreenCleanComponent())
                .collidable()
                .build();

        return pipe;
    }
}
