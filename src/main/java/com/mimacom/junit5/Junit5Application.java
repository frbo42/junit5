package com.mimacom.junit5;

import com.mimacom.junit5.core.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Junit5Application {

    public static void main(String[] args) {
        SpringApplication.run(Junit5Application.class, args);

        Item item = new Item();
        item.update("first");
    }
}