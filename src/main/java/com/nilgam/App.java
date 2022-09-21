package com.nilgam;

import java.util.ArrayList;
import java.util.Map;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

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
        settings.setTitle("Basic Game App");
        settings.setVersion("0.1");
        // settings.setFullScreenFromStart(true);
        settings.setPreserveResizeRatio(true);
    }

    private Entity player;
    private Point2D playerPreviousPosition;
    // private Entity brick;

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new GameEntityFactory());
        player = FXGL.spawn("player", FXGL.getAppWidth() / 2, FXGL.getAppHeight() / 2);

        for (int i = 0; i < 4; i++) {
            FXGL.spawn("brick", FXGL.random(0, FXGL.getAppWidth()), FXGL.random(0, FXGL.getAppHeight()));
        }

        // FXGL.getGameScene().getViewport().bindToEntity(player, FXGL.getAppWidth() / 2, FXGL.getAppHeight() / 2);
        // FXGL.getGameScene().getViewport().setBounds(-400, -400, 2000, 2000);
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.E, "Move right", () -> {
            player.getComponent(PlayerComponent.class).moveRight();
        });

        FXGL.onKey(KeyCode.U, "Move left", () -> {
            player.getComponent(PlayerComponent.class).moveLeft();
        });

        FXGL.onKey(KeyCode.P, "Move up", () -> {
            player.getComponent(PlayerComponent.class).moveUp();
        });

        FXGL.onKey(KeyCode.I, "Move dowm", () -> {
            player.getComponent(PlayerComponent.class).moveDown();
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
        vars.put("blocks", new ArrayList<Entity>());
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100
        textPixels.textProperty().bind(FXGL.getWorldProperties().intProperty("pixelsMoved").asString());

        Text textPlayerX = new Text();
        textPlayerX.setTranslateX(50);
        textPlayerX.setTranslateY(120);
        textPlayerX.textProperty().bind(player.xProperty().asString());

        Text textPlayerY = new Text();
        textPlayerY.setTranslateX(50);
        textPlayerY.setTranslateY(140);
        textPlayerY.textProperty().bind(player.yProperty().asString());

        FXGL.getGameScene().addUINode(textPixels); // add to the scene graph
        FXGL.getGameScene().addUINode(textPlayerX);
        FXGL.getGameScene().addUINode(textPlayerY);
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.BRICK) {
            @Override
            protected void onCollisionBegin(Entity player, Entity brick) {
                // System.out.println("COLLIDE");
            }

        });
    }

}
