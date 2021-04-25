package com.restaurant.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(RestaurantRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Restaurant("KFC", new ArrayList<String>(), new ArrayList<Chef>(), 9, "cambridge", "ON")));
        };
    }
}
