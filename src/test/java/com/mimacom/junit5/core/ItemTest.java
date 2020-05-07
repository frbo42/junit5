package com.mimacom.junit5.core;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import javax.swing.event.DocumentEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

class ItemTest {
    @Test
    void testDomainEvent() {
        ApplicationEventPublisher mock = mock(ApplicationEventPublisher.class);
        EventPublisher.set(mock);

        new Item().update("newValue");

        verify(mock).publishEvent(any(DomainEvent.class));
        reset(mock);
    }
}