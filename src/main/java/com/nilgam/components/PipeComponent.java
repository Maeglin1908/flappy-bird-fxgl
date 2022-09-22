package com.nilgam.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.nilgam.EntityType;

public class PipeComponent extends Component {

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(-180 * tpf);
    }

    @Override
    public void onRemoved() {

            FXGL.inc("pipesDone", 0.5);

    }

}
