package com.mimacom.junit5.core;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class RuleTest {
    @Rule
    public EventRule eventRule = new EventRule();

    @Test
    public void name() {
        new Item().update("newValue");

        eventRule.verifyEventType(DomainEvent.class);
    }
}