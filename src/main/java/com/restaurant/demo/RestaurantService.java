package com.restaurant.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ChefRepository chefRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, ChefRepository chefRepository){
        this.restaurantRepository = restaurantRepository;
        this.chefRepository = chefRepository;
    }

    public Restaurant save (Restaurant restaurant){
        List<Chef> chefs = new ArrayList<>();
        for (Chef chef : restaurant.getRestaurant_chefs()){
            chefs.add(chefRepository.save(chef));
        }
        Restaurant restaurantTemp = new Restaurant();
        restaurantTemp = restaurantRepository.save(restaurantTemp);
        restaurantTemp.setRestaurant_name(restaurant.getRestaurant_name());
        restaurantTemp.setRestaurant_cuisines(restaurant.getRestaurant_cuisines());
        restaurantTemp.setRestaurant_rating(restaurant.getRestaurant_rating());
        restaurantTemp.setRestaurant_city(restaurant.getRestaurant_city());
        restaurantTemp.setRestaurant_province(restaurant.getRestaurant_province());
        for (Chef chef : chefs){
            restaurantTemp.addRestaurant_chef(chef);
        }
        return restaurantRepository.save(restaurantTemp);
    }
}
