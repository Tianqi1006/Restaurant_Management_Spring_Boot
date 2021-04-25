package com.restaurant.demo;

public class RestaurantNotFoundException extends RuntimeException {
    RestaurantNotFoundException(Long id) {
        super("Could not find restaurant " + id);
    }
}
