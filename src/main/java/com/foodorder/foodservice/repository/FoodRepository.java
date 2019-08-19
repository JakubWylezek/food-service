package com.foodorder.foodservice.repository;

import com.foodorder.foodservice.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByNameIgnoreCase(String name);

    Set<Food> findFoodsByNameContainsIgnoreCase(String name);
}
