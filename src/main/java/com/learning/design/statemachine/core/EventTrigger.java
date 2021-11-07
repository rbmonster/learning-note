package com.learning.design.statemachine.core;

public class EventTrigger<E extends Enum<E>> {
    private final E event;

    public EventTrigger(E event) {
        this.event = event;
    }

    public E getEvent() {
        return event;
    }
}
