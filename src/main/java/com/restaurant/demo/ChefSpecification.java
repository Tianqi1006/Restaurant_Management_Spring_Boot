package com.restaurant.demo;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;

public class ChefSpecification implements Specification<Chef> {
    private String columnName;
    private String searchValue;

    public ChefSpecification(String columnName, String searchValue){
        this.columnName = columnName;
        this.searchValue = searchValue;
    }


    @Override
    public Predicate toPredicate(Root<Chef> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (columnName.equalsIgnoreCase("restaurant_id")) {
            return criteriaBuilder.equal(root.get("restaurant").get("restaurant_id"), searchValue);
        }
        else if (columnName.equalsIgnoreCase("cuisine")) {
            Expression<Collection<String>> column = root.get("chef_cuisines");
            return criteriaBuilder.isMember(this.searchValue, column);
        }
        else {
            columnName = "chef_" + columnName;
            return criteriaBuilder.equal(root.get(columnName), searchValue);
        }
    }
}
