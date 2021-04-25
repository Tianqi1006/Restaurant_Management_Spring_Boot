package com.restaurant.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

public class RestaurantSpecification implements Specification<Restaurant> {
    private String columnName;
    private String searchValue;

    public RestaurantSpecification(String columnName, String searchValue){
        this.columnName = columnName;
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder){
        if (columnName.equalsIgnoreCase("restaurant_cuisines")){
            Expression<Collection<String>> column = root.get("restaurant_cuisines");
            return builder.isMember(this.searchValue, column);
        }
        else {
            return builder.and(builder.equal(root.<String>get(this.columnName), this.searchValue));
        }
    }
}

//@Service
//public class RestaurantService {
//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    public List<Restaurant> findByPara(String columnName, String searchValue){
//        RestaurantSpecification rs = new RestaurantSpecification(columnName, searchValue);
//        return restaurantRepository.find(rs);
//    }
//}
