package com.mimacom.junit5.core;

public class DomainEvent {
    private final String value;

    public DomainEvent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}