package com.learning.design.statemachine.core;

public interface StateMachineExecutor<S extends Enum<S>, A extends Enum<A>, E extends Enum<E>, T, C extends StateMachineContext<S,T>> {
    EventTrigger<E> execute(Action<A> action, final C executionContext);
    EventTrigger<E> doExecute(Action<A> action, final T payload);
    boolean initPayload(C executionContext);
}
