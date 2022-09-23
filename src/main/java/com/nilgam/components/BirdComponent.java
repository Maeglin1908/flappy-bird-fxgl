package com.nilgam.components;

import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.entity.component.Component;

public class BirdComponent extends Component {

    private double velocity = 0;

    public void jump() {
        velocity = -geti("lift");
    }

    @Override
    public void onUpdate(double tpf) {
        inc("score", 1.0);
        velocity += getd("gravity") * tpf;
        entity.translateY(velocity);

    }
}
