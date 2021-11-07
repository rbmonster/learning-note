package com.learning.design.statemachine.collectionmode;

import org.springframework.util.Assert;

public class DoMachineMain {

    public static void main(String[] args) {
        EntranceMachine entranceMachine = new EntranceMachine(EntranceMachineState.LOCKED);

        String result = entranceMachine.execute(Action.PASS);

        Assert.isTrue("alarm".equals(result),"");
        Assert.isTrue(entranceMachine.getState() == EntranceMachineState.LOCKED, "");

    }
}
