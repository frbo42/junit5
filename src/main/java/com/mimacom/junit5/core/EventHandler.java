package com.mimacom.junit5.core;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {
    @EventListener
    public void onEvent(DomainEvent event){
        System.out.println(event.getValue());
    }
}