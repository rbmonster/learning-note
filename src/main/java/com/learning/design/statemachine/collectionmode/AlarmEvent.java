package com.learning.design.statemachine.collectionmode;


public class AlarmEvent implements Event {
    @Override
    public String execute() {
        return "alarm";
    }
}
