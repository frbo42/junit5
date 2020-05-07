package com.mimacom.junit5.core;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderExtension implements
        BeforeAllCallback,
//        BeforeAll,
        BeforeEachCallback,
//        BeforeEach,
        BeforeTestExecutionCallback,
//            Test,
        AfterTestExecutionCallback,
//        AfterEach,
        AfterEachCallback,
//        AfterAll,
        AfterAllCallback
        ,
        TestInstancePostProcessor,
        ParameterResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        LOGGER.debug("BeforeEachCallback -> beforeEach");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        LOGGER.debug("AfterTestExecutionCallback -> afterTestExecution");
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        LOGGER.debug("TestInstancePostProcessor -> postProcessTestInstance");
    }


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        LOGGER.debug("ParameterResolver -> supportsParameter");
        return true;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context) throws ParameterResolutionException {
        LOGGER.debug("ParameterResolver -> resolveParameter");
        return "param";
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        LOGGER.debug("BeforeAllCallback -> beforeAll");

    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        LOGGER.debug("BeforeTestExecutionCallback -> beforeTestExecution");

    }

    @Override
    public void afterEach(ExtensionContext context) {
        LOGGER.debug("AfterEachCallback -> afterEach");

    }

    @Override
    public void afterAll(ExtensionContext context) {
        LOGGER.debug("AfterAllCallback -> afterAll");
    }
}