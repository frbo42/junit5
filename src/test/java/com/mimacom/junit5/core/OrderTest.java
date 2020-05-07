package com.mimacom.junit5.core;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(OrderExtension.class)
public class OrderTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTest.class);

    @BeforeAll
    static void beforeAll() {
        LOGGER.debug("beforeAll");
    }

    @BeforeEach
    void setUp() {
        LOGGER.debug("setUp");
    }

    @Test
    public void test() {
        LOGGER.debug("test");
        new Item();
    }

    @AfterEach
    void tearDown() {
        LOGGER.debug("tearDown");
    }

    @AfterAll
    static void afterAll() {
        LOGGER.debug("afterAll");
    }
}