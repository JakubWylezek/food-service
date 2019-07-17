package com.foodorder.foodservice.service;

import com.foodorder.foodservice.model.Food;
import com.foodorder.foodservice.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public Food getFoodByName(String name) {
        return foodRepository.findByNameIgnoreCase(name).get();
    }

    public Set<Food> findFoodByName(String name) {
        return foodRepository.findFoodsByNameContainsIgnoreCase(name);
    }
}
