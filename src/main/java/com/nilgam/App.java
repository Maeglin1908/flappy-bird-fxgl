package com.nilgam;

import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.nilgam.components.BirdComponent;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Hello world!
 *
 */
public class App extends GameApplication {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Flappy Bird - FXGL Version");
        settings.setVersion("0.1");
        // settings.setFullScreenFromStart(true);
        settings.setPreserveResizeRatio(true);
    }

    private Entity bird;

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new GameEntityFactory());
        bird = FXGL.spawn("bird");
        FXGL.getGameTimer().runAtInterval(() -> {
            FXGL.spawn("pipetop");
            FXGL.spawn("pipebottom");
        }, Duration.seconds(2));
    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Jump") {
            // @Override
            // protected void onAction() {
            // }

            @Override
            protected void onActionBegin() {
                bird.getComponent(BirdComponent.class).jump();
            }
            // @Override
            // protected void onActionEnd() {
            // }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("birdOffset", 30.0);
        vars.put("birdWidth", 30.0);
        vars.put("birdHeight", 30.0);

        vars.put("pipeSpace", (double) (FXGL.getAppHeight() / 4));
        vars.put("pipeWidth", (double) (FXGL.getAppWidth() / 10));

        vars.put("score", 0.0);
        vars.put("pipesDone", 0.0);
    }

    @Override
    protected void initUI() {
        Text textScore = new Text();
        textScore.setTranslateX(50);
        textScore.setTranslateY(100);
        textScore.textProperty().bind(FXGL.getdp("score").asString());

        Text textPipes = new Text();
        textPipes.setTranslateX(50);
        textPipes.setTranslateY(120);
        textPipes.textProperty().bind(FXGL.getdp("pipesDone").asString());

        FXGL.addUINode(textScore);
        FXGL.addUINode(textPipes);
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld()
                .addCollisionHandler(new CollisionHandler(EntityType.BIRD, EntityType.PIPE) {
                    @Override
                    protected void onCollisionBegin(Entity bird, Entity brick) {
                        bird.getComponent(BirdComponent.class).gameOver();
                    }

                });
    }

}
