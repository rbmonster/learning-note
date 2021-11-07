package com.learning.design.statemachine.core;

public class Transition<S extends Enum<S>, C extends Enum<C>, E extends Enum<E>> {
    private State<S,C> source;

    private State<S,C> target;

    private EventTrigger<E> trigger;

    private Transition(TransitionBuilder<S,C,E> transitionBuilder) {
        this.source = transitionBuilder.source;
        this.target = transitionBuilder.target;
        this.trigger = transitionBuilder.trigger;
    }

    public State<S,C> getSource() {
        return source;
    }

    public State<S,C> getTarget() {
        return target;
    }

    public EventTrigger<E> getTrigger() {
        return trigger;
    }

    public static class TransitionBuilder<S extends Enum<S>, C extends Enum<C>, E extends Enum<E>> {
        private State<S,C> source;
        private State<S,C> target;
        private EventTrigger<E> trigger;

        public TransitionBuilder<S,C,E> from(State<S,C> source) {
            this.source = source;
            return this;
        }

        public TransitionBuilder<S,C,E> to(State<S,C> target) {
            this.target = target;
            return this;
        }

        public TransitionBuilder<S,C,E> when(EventTrigger<E> trigger) {
            this.trigger = trigger;
            return this;
        }

        public Transition<S,C,E> build() {
            return new Transition<>(this);
        }
    }
}
