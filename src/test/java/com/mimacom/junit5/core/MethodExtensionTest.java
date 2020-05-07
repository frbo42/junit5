package com.mimacom.junit5.core;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

class MethodExtensionTest {

    @Test
    @ExtendWith(EventExtension.class)
    void extensionTest(EventExtension.EventAssert assertEvent) {
        new Item().update("newValue");

        assertEvent.verifyPublished(DomainEvent.class);
    }
}