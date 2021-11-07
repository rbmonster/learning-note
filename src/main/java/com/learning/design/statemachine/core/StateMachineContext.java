package com.learning.design.statemachine.core;

public interface StateMachineContext<S extends Enum<S>, T> {
    S getCurrentState();
    void setCurrentState(S state);
    String getId();
    void setId(String id);
    T getPayload();
    void setPayload(T payload);
}
