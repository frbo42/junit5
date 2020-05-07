package com.mimacom.junit5.core;

public class Item {
    String value;

    public void update(String newValue) {
        value = newValue;
        EventPublisher.publish(new DomainEvent(value));
    }
}
