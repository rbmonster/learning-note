package com.learning.design.statemachine.enummode;

import org.springframework.util.Assert;

public class DoMachineMain {

    public static void main(String[] args) {
        EntranceMachine entranceMachine = new EntranceMachine(EntranceMachineState.LOCKED);

        String result = entranceMachine.execute(Action.INSERT_COIN);

        Assert.isTrue("opened".equals(result),"");
        Assert.isTrue(entranceMachine.getState() == EntranceMachineState.UNLOCKED, "");

    }
}
