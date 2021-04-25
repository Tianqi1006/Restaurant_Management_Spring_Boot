package com.restaurant.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChefRepository extends JpaRepository<Chef, Long>, JpaSpecificationExecutor<Chef> {
}
