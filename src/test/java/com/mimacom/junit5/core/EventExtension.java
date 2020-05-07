package com.mimacom.junit5.core;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class EventExtension implements BeforeAllCallback, AfterTestExecutionCallback, TestInstancePostProcessor, ParameterResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventExtension.class);
    private static final Class<EventPublisher> KEY = EventPublisher.class;
    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(EventExtension.class);

    @Override
    public void beforeAll(ExtensionContext context) {
        LOGGER.debug("BeforeAllCallback -> beforeAll");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        LOGGER.debug("AfterTestExecutionCallback -> afterTestExecution");
        reset(getPublisher(context));
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        LOGGER.debug("TestInstancePostProcessor -> postProcessTestInstance");
        Arrays.stream(testInstance.getClass().getDeclaredFields())
                .filter(field -> field.getType() == EventAssert.class)
                .forEach(field -> injectAssert(testInstance, field, getPublisher(context)));
    }

    private ApplicationEventPublisher getPublisher(ExtensionContext context) {
        ApplicationEventPublisher publisher = context.getStore(NAMESPACE).get(KEY, ApplicationEventPublisher.class);
        if(publisher == null){
            ApplicationEventPublisher mockPublisher = mock(ApplicationEventPublisher.class);
            context.getStore(NAMESPACE).put(KEY, mockPublisher);
            EventPublisher.set(mockPublisher);
        }
        return context.getStore(NAMESPACE).get(KEY, ApplicationEventPublisher.class);
    }

    private void injectAssert(Object testInstance, Field field, ApplicationEventPublisher mockPublisher) {
        try {
            field.setAccessible(true);
            field.set(testInstance, new EventAssert(mockPublisher));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        LOGGER.debug("ParameterResolver -> supportsParameter");
        return parameterContext.getParameter().getType() == EventAssert.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context) throws ParameterResolutionException {
        LOGGER.debug("ParameterResolver -> resolveParameter");
        return new EventAssert(getPublisher(context));
    }

    public static class EventAssert {
        private final ApplicationEventPublisher mockPublisher;

        public EventAssert(ApplicationEventPublisher mockPublisher) {
            this.mockPublisher = mockPublisher;
        }

        public void verifyPublished(Class<? extends DomainEvent> expectedType) {
            ArgumentCaptor<DomainEvent> captor = ArgumentCaptor.forClass(DomainEvent.class);

            verify(mockPublisher).publishEvent(captor.capture());
            DomainEvent value = captor.getValue();

            assertThat(value.getClass())
                    .as("Found event: %s should have been one of: %s", value, expectedType)
                    .isEqualTo(expectedType);
        }
    }
}