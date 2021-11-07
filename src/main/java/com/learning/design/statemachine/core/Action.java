package com.learning.design.statemachine.core;

public class Action<C extends Enum<C>> {
    private final C id;

    public Action(C id) {
        this.id = id;
    }

    public C getId() {
        return id;
    }
}
