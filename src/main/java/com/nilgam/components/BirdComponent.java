package com.nilgam.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class BirdComponent extends Component {

    private double gravity = 15.8;
    private double velocity = 0;

    public void jump() {
        velocity = -6;
    }

    @Override
    public void onUpdate(double tpf) {
        FXGL.inc("score", 1.0);
        velocity += gravity * tpf;
        entity.translateY(velocity);

        if (!entity.isWithin(FXGL.getGameScene().getViewport().getVisibleArea())) {
            gameOver();
        }

    }

    public void gameOver() {
        FXGL.showMessage("YOU LOSE !!!!", () -> FXGL.getGameController().startNewGame());
    }
}
