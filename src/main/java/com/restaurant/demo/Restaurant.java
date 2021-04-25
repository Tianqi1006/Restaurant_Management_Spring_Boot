package com.restaurant.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Restaurant {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long restaurant_id;
    private String restaurant_name;
    private @ElementCollection List<String> restaurant_cuisines = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.LAZY)
//    @JoinColumn(name = "restaurant_id")
    private  List<Chef> restaurant_chefs = new ArrayList<>();
    private int restaurant_rating;
    private String restaurant_city;
    private String restaurant_province;

    public Restaurant() {}

    Restaurant(String restaurant_name, List<String> restaurant_cuisines, List<Chef> restaurant_chefs, int restaurant_rating, String restaurant_city, String restaurant_province) {
        this.restaurant_name = restaurant_name;
        this.restaurant_cuisines = restaurant_cuisines;
        this.restaurant_chefs = restaurant_chefs;
        this.restaurant_rating = restaurant_rating;
        this.restaurant_city = restaurant_city;
        this.restaurant_province = restaurant_province;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public List<String> getRestaurant_cuisines() {
        return restaurant_cuisines;
    }

    public List<Chef> getRestaurant_chefs() {
        return restaurant_chefs;
    }

    public int getRestaurant_rating() {
        return restaurant_rating;
    }

    public String getRestaurant_city() {
        return restaurant_city;
    }

    public String getRestaurant_province() {
        return restaurant_province;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public void setRestaurant_cuisines(List<String> restaurant_cuisines) {
        this.restaurant_cuisines = restaurant_cuisines;
    }

    public void setRestaurant_chefs(List<Chef> restaurant_chefs) {
//        for (Chef chef : restaurant_chefs){
//            chef.setRestaurant(this);
//        }
        this.restaurant_chefs = restaurant_chefs;
    }

    public void addRestaurant_chef(Chef restaurant_chef){
        restaurant_chefs.add(restaurant_chef);
        restaurant_chef.setRestaurant(this);
    }

    public void setRestaurant_rating(int restaurant_rating) {
        this.restaurant_rating = restaurant_rating;
    }

    public void setRestaurant_city(String restaurant_city) {
        this.restaurant_city = restaurant_city;
    }

    public void setRestaurant_province(String restaurant_province) {
        this.restaurant_province = restaurant_province;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if(!(o instanceof Restaurant)) {
            return false;
        }
        Restaurant restaurant = (Restaurant) o;
        return Objects.equals(this.restaurant_id, restaurant.restaurant_id) && Objects.equals(this.restaurant_name, restaurant.restaurant_name)
                && Objects.equals(this.restaurant_cuisines, restaurant.restaurant_cuisines)
                && Objects.equals(this.restaurant_chefs, restaurant.restaurant_chefs)
                && Objects.equals(this.restaurant_rating, restaurant.restaurant_rating)
                && Objects.equals(this.restaurant_city, restaurant.restaurant_city)
                && Objects.equals(this.restaurant_province, restaurant.restaurant_province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.restaurant_id, this.restaurant_name, this.restaurant_cuisines, this.restaurant_chefs, this.restaurant_rating, this.restaurant_city, this.restaurant_province);
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + this.restaurant_id + ", name='" + this.restaurant_name + '\'' + ", cuisines='" + this.restaurant_cuisines + '\''
                + ", chefs='" + this.restaurant_chefs + '\'' + ", rating='" + this.restaurant_rating + '\''
                + ", city='" + this.restaurant_city + '\'' + ", province='" + this.restaurant_province + '\''
                + '}';
    }

}

