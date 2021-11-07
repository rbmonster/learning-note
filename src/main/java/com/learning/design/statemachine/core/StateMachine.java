package com.learning.design.statemachine.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StateMachine<S extends Enum<S>, A extends Enum<A>, E extends Enum<E>, T, C extends StateMachineContext<S,T>> {

    private final Collection<State<S,A>> states;
    private final Collection<Transition<S, A, E>> transitions;
    private final State<S,A> initialState;
    private final StateMachineExecutor<S,A,E,T,C> stateMachineExecutor;
    private final C executionContext;

    private State<S,A> currentState;

    private StateMachine(StateMachineBuilder<S,A,E,T,C> stateMachineBuilder) {
        this.states = stateMachineBuilder.states;
        this.transitions = stateMachineBuilder.transitions;
        this.initialState = stateMachineBuilder.initialState;
        this.stateMachineExecutor = stateMachineBuilder.stateMachineExecutor;
        this.executionContext = stateMachineBuilder.executionContext;
    }

    public State<S,A> getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State<S,A> currentState) {
        this.currentState = currentState;
    }

    public final void start() {
        if (currentState == null) {
            currentState = initialState;
        }

        boolean transited = false;
        do {
            Action<A> action = currentState.getAction();
            if (action != null) {
                this.executionContext.setCurrentState(currentState.getId());
                EventTrigger<E> trigger = stateMachineExecutor.execute(action, this.executionContext);
                transited = transitState(trigger);
            } else {
                break;
            }
        } while (transited);
    }

    private boolean transitState(EventTrigger<E> trigger) {
        if (trigger == null) {
            return false;
        }
        List<Transition<S, A, E>> transitionList = transitions.stream().filter(transition -> {
            return currentState.getId() == transition.getSource().getId() && trigger.getEvent() == transition.getTrigger().getEvent();
        }).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(transitionList)) {
            if (transitionList.size() > 1) {
                log.error(new StringBuilder("multiple transition from state ").append(currentState.getId()).append(" and trigger ").append(trigger.getEvent()).toString());
                return false;
            }
            Transition<S, A, E> transition = transitionList.get(0);
            State<S,A> target = transition.getTarget();
            currentState = target;
            return true;
        } else {
            log.error(new StringBuilder("invalid trigger ").append(trigger.getEvent()).append(" for state ").append(currentState.getId()).toString());
            return false;
        }
    }

    public static class StateMachineBuilder<S extends Enum<S>, A extends Enum<A>, E extends Enum<E>, T, C extends StateMachineContext<S,T>> {
        private Collection<State<S,A>> states;
        private Collection<Transition<S, A, E>> transitions;
        private State<S,A> initialState;
        private StateMachineExecutor<S,A,E,T,C> stateMachineExecutor;
        private C executionContext;

        public StateMachineBuilder<S,A,E,T,C> states(Collection<State<S,A>> states) {
            this.states = states;
            return this;
        }

        public StateMachineBuilder<S,A,E,T,C> transitions(Collection<Transition<S, A, E>> transitions) {
            this.transitions = transitions;
            return this;
        }

        public StateMachineBuilder<S,A,E,T,C> initialState(State<S,A> initialState) {
            this.initialState = initialState;
            return this;
        }

        public StateMachineBuilder<S,A,E,T,C> executor(StateMachineExecutor<S,A,E,T,C> stateMachineExecutor) {
            this.stateMachineExecutor = stateMachineExecutor;
            return this;
        }

        public StateMachineBuilder<S,A,E,T,C> executionContext(C executionContext) {
            this.executionContext = executionContext;
            return this;
        }

        public StateMachine<S,A,E,T,C> build() {
            return new StateMachine<>(this);
        }
    }

}
