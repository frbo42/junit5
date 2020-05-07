package com.mimacom.junit5.core;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class EventPublisher {
    static ApplicationEventPublisher eventPublisher;

    EventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    static void publish(DomainEvent event) {
        eventPublisher.publishEvent(event);
    }

    static void set(ApplicationEventPublisher publisher){
        eventPublisher = publisher;
    }
}