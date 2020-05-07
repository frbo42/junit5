package com.mimacom.junit5.core;

import org.junit.rules.ExternalResource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

class EventRule extends ExternalResource {
    ApplicationEventPublisher mockPublisher;

    @Override
    public void before() {
        mockPublisher = mock(ApplicationEventPublisher.class);
        EventPublisher.set(mockPublisher);
    }

    @Override
    public void after() {
        reset(mockPublisher);
    }

    void verifyEventType(Class<? extends DomainEvent> expectedType) {
        ArgumentCaptor<DomainEvent> captor = ArgumentCaptor.forClass(DomainEvent.class);

        Mockito.verify(mockPublisher).publishEvent(captor.capture());
        DomainEvent value = captor.getValue();

        assertThat(value.getClass())
                .as("Found event: %s should have been one of: %s", value, expectedType)
                .isEqualTo(expectedType);
    }
}