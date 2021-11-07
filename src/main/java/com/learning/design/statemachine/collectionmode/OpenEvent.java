package com.learning.design.statemachine.collectionmode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenEvent implements Event {
    @Override
    public String execute() {
        return "open";
    }
}
