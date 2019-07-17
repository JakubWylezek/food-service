package com.foodorder.foodservice.repository;

import com.foodorder.foodservice.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {

    Optional<Food> findByNameIgnoreCase(String name);

    Set<Food> findFoodsByNameContainsIgnoreCase(String name);
}
