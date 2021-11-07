package com.learning.design.statemachine.enummode;

import lombok.Data;

import java.util.Objects;

@Data
public class EntranceMachine {
    private EntranceMachineState state;

    public EntranceMachine(EntranceMachineState state) {
        setState(state);
    }

    public String execute(Action action) {
        if (Objects.isNull(action)) {
            throw new IllegalStateException();
        }

        return action.execute(this, state);
    }

    public String open() {
        return "opened";
    }

    public String alarm() {
        return "alarm";
    }

    public String refund() {
        return "refund";
    }

    public String close() {
        return "closed";
    }


}
