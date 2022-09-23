package com.nilgam.components;

import static com.almasb.fxgl.dsl.FXGL.*;

import com.almasb.fxgl.entity.component.Component;

public class PipeComponent extends Component {

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(-getd("pipeSpeed") * tpf);
    }

    @Override
    public void onRemoved() {
        inc("pipesDone", 0.5);
    }

}
