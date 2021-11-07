package com.learning.design.statemachine.core;

public class State<S extends Enum<S>, C extends Enum<C>> {
    private final S id;
    private final Action<C> action;

    public State(S id, Action<C> action) {
        this.id = id;
        this.action = action;
    }

    public S getId() {
        return id;
    }

    public Action<C> getAction() {
        return action;
    }
}
