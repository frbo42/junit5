package com.mimacom.junit5.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EventExtension.class)
class ExtensionTest {
    EventExtension.EventAssert assertEvent;

    @Test
     void extensionTest() {
        new Item().update("newValue");

        assertEvent.verifyPublished(DomainEvent.class);
    }

}