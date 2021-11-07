package com.learning.design.statemachine.collectionmode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntranceMachineTransaction {

    private EntranceMachineState currentState;

    private Action action;

    private EntranceMachineState nextState;

    private Event event;

}
