package com.restaurant.demo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.net.httpserver.Authenticator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final ChefRepository chefRepository;
    private final RestaurantService restaurantService;
    private final ChefService chefService;

    RestaurantController(RestaurantRepository restaurantRepository, ChefRepository chefRepository, RestaurantService restaurantService, ChefService chefService) {
        this.restaurantRepository = restaurantRepository;
        this.chefRepository = chefRepository;
        this.restaurantService = restaurantService;
        this.chefService = chefService;
    }


    @PostMapping("/restaurants")
    ResponseEntity<?> newRestaurant(@RequestBody Restaurant request, HttpServletResponse response) {
        try{
            Restaurant result = restaurantService.save(request);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return new ResponseEntity<>("restaurant_id: "+result.getRestaurant_id()+", created_time: "+timestamp, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Single item
    @GetMapping("/restaurants/{id}")
    Restaurant one(@PathVariable Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @GetMapping("/restaurants")
    @ResponseBody
    List<Restaurant> getRestaurants(@RequestParam Map<String, String> paramMap) {
        List<RestaurantSpecification> specs = new ArrayList<>();
        for (String key : paramMap.keySet()) {
            specs.add(new RestaurantSpecification(key, paramMap.get(key)));
        }

        Specification combinedSpec = specs.get(0);

        for (int i = 1; i < specs.size(); i++) {
            combinedSpec = Specification.where(combinedSpec).and(specs.get(i));
        }
        return restaurantRepository.findAll(combinedSpec);
    }

    @GetMapping("/chefs")
    @ResponseBody
    List<Chef> getChefs(@RequestParam Map<String, String> paramMap){
        List<ChefSpecification> specs = new ArrayList<>();
        for (String key : paramMap.keySet()){
            specs.add(new ChefSpecification(key, paramMap.get(key)));
        }
        Specification combinedSpec = specs.get(0);

        for (int i = 1; i < specs.size(); i++){
            combinedSpec = Specification.where(combinedSpec).and(specs.get(i));
        }

        return chefRepository.findAll(combinedSpec);

    }

    @PutMapping("/restaurants/{id}")
    ResponseEntity<?> replaceRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable Long id) {
        try {
            Optional flag = restaurantRepository.findById(id);
            if (!flag.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                restaurantRepository.findById(id)
                        .map(restaurant -> {
                            restaurant.setRestaurant_name(newRestaurant.getRestaurant_name());
                            restaurant.setRestaurant_cuisines(newRestaurant.getRestaurant_cuisines());
                            restaurant.setRestaurant_chefs(newRestaurant.getRestaurant_chefs());
                            restaurant.setRestaurant_rating(newRestaurant.getRestaurant_rating());
                            restaurant.setRestaurant_city(newRestaurant.getRestaurant_city());
                            restaurant.setRestaurant_province(newRestaurant.getRestaurant_province());
                            return restaurantRepository.save(restaurant);
                        });
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                return new ResponseEntity<>("restaurant_id: " + id + ", created_time: " + timestamp, HttpStatus.OK);
            }
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/restaurants/{id}")
    ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        Optional flag = restaurantRepository.findById(id);
        if (!flag.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            try {
                restaurantRepository.deleteById(id);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                return new ResponseEntity<>("restaurant_id: "+ id + ", delete_data: " + timestamp, HttpStatus.OK);
            } catch (IllegalArgumentException ex) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }
}



