package com.learning.design.statemachine.collectionmode;

public class CloseEvent implements Event {
    @Override
    public String execute() {
        return "close";
    }
}
