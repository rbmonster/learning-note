package com.learning.design.statemachine.core;

public abstract class AbstractStateMachineExecutor<S extends Enum<S>, A extends Enum<A>, E extends Enum<E>, T, C extends StateMachineContext<S,T>> implements StateMachineExecutor<S, A, E, T, C> {
    @Override
    final public EventTrigger<E> execute(Action<A> action, C executionContext) {
        if (initPayload(executionContext)) {
            return doExecute(action, executionContext.getPayload());
        } else {
            return null;
        }
    }
}
