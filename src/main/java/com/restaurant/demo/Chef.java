package com.restaurant.demo;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
class Chef {
    @Id
    @GeneratedValue
    Long chef_id;
    private String chef_name;
    private int chef_salary;
    private @ElementCollection
    List<String> chef_cuisines = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Chef() {}

    Chef(String chef_name, int chef_salary, List<String> chef_cuisines) {
        this.chef_name = chef_name;
        this.chef_salary = chef_salary;
        this.chef_cuisines = chef_cuisines;
    }

    public Long getChef_id() {
        return chef_id;
    }

    public String getChef_name() {
        return chef_name;
    }

    public int getChef_salary() {
        return chef_salary;
    }

    public List<String> getChef_cuisines() {
        return chef_cuisines;
    }

    @JsonIgnore
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setChef_id(Long chef_id) {
        this.chef_id = chef_id;
    }

    public void setChef_name(String chef_name) {
        this.chef_name = chef_name;
    }

    public void setChef_salary(int chef_salary) {
        this.chef_salary = chef_salary;
    }

    public void setChef_cuisines(List<String> chef_cuisines) {
        this.chef_cuisines = chef_cuisines;
    }

    @JsonIgnore
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Chef)) {
            return false;
        }
        Chef chef = (Chef) o;
        return Objects.equals(this.chef_name, chef.chef_name) && Objects.equals(this.chef_salary, chef.chef_salary)
                && Objects.equals(this.chef_cuisines, chef.chef_cuisines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.chef_name, this.chef_salary, this.chef_cuisines);
    }

    @Override
    public String toString() {
        return "Chef{" + "name=" + this.chef_name + '\'' + ", salary='" + this.chef_salary + '\''
                + ", cuisines='" + this.chef_cuisines + '\'' + '}';
    }
}

