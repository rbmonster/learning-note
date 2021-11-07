package com.learning.design.statemachine.collectionmode;


public class RefundEvent implements Event {
    @Override
    public String execute() {
        return "refund";
    }
}
