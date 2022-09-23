package com.nilgam;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.time.TimerAction;
import com.nilgam.components.BirdComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

/**
 * Hello world!
 */
public class App extends GameApplication {
    TimerAction spawnPipes;
    private Entity bird;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Flappy Bird - FXGL Version");
        settings.setVersion("0.1");
        settings.setPreserveResizeRatio(true);

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GameEntityFactory());
        bird = spawn("bird");
        spawnPipes = getGameTimer().runAtInterval(() -> {
            spawn("pipeTop");
            spawn("pipeBottom");
        }, Duration.seconds(2));


    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onActionBegin() {
                bird.getComponent(BirdComponent.class).jump();
            }
        }, KeyCode.SPACE);
        getInput().addAction(new UserAction("Skip") {
            @Override
            protected void onAction() {
                getGameScene().step(0.1);
            }
        }, KeyCode.E);


    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("birdOffset", 30.0);
        vars.put("birdWidth", 30.0);
        vars.put("birdHeight", 30.0);

        vars.put("pipeSpeed", 220.0);
        vars.put("pipeSpace", 120.0);
        vars.put("pipeWidth", 80.0);

        vars.put("score", 0.0);
        vars.put("pipesDone", 0.0);

        vars.put("gravity", 13.0);
        vars.put("lift", 6);
    }

    @Override
    protected void initUI() {
        Text textScore = new Text();
        textScore.setTranslateX(50);
        textScore.setTranslateY(100);
        textScore.textProperty().bind(
                getdp("score").asString()
        );

        Text textPipes = new Text();
        textPipes.setTranslateX(50);
        textPipes.setTranslateY(120);
        textPipes.textProperty().bind(
                getdp("pipesDone").asString()
        );

        addUINode(textScore);
        addUINode(textPipes);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld()
                .addCollisionHandler(
                        new CollisionHandler(EntityType.BIRD, EntityType.PIPE) {
                            @Override
                            protected void onCollisionBegin(Entity bird, Entity brick) {
                                bird.removeFromWorld();
                                gameOver();
                            }
                        });
    }

    @Override
    protected void onUpdate(double tpf) {
        if (!bird.isVisible()) {
            bird.removeFromWorld();
            gameOver();
        }
    }

    public void gameOver() {
        spawnPipes.expire();
        showMessage("YOU LOSE !!!!",
                () -> getGameController().startNewGame());
    }

}
