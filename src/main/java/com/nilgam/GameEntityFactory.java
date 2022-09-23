package com.nilgam;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.OffscreenInvisibleComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.nilgam.components.BirdComponent;
import com.nilgam.components.PipeComponent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameEntityFactory implements EntityFactory {

    @Spawns("bird")
    public Entity newBird(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.BIRD)
                .at(getWorldProperties().getDouble("birdOffset"), (getAppHeight() / 2.0))
                .viewWithBBox(new Rectangle(
                        getWorldProperties().getDouble("birdWidth"),
                        getWorldProperties().getDouble("birdHeight"),
                        Color.BLUE))
                .with(new BirdComponent())
                .with(new OffscreenInvisibleComponent())
                .collidable()
                .build();
    }

    @Spawns("pipeTop")
    public Entity newPipeTop(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.PIPE)
                .at(getAppWidth(), 0.0)
                .viewWithBBox(new Rectangle(
                        getWorldProperties().getDouble("pipeWidth"),
                        random(0,
                                getAppHeight() - getWorldProperties().getDouble("pipeSpace"))

                ))
                .with(new PipeComponent())
                .with(new OffscreenCleanComponent())
                .collidable()
                .build();
    }

    @Spawns("pipeBottom")
    public Entity newBrick(SpawnData data) {
        List<Entity> pipes = getGameWorld().getEntitiesByType(EntityType.PIPE);
        Entity lastPipe = pipes.get(pipes.size() - 1);
        double pipeY = lastPipe.getHeight() + getWorldProperties().getDouble("pipeSpace");

        return entityBuilder(data)
                .type(EntityType.PIPE)
                .at(getAppWidth(), pipeY)
                .viewWithBBox(new Rectangle(
                        getWorldProperties().getDouble("pipeWidth"),
                        getAppHeight() - pipeY))
                .with(new PipeComponent())
                .with(new OffscreenCleanComponent())
                .collidable()
                .build();
    }
}
