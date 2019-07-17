package com.foodorder.foodservice.service;

import com.foodorder.foodservice.dto.FoodDto;
import com.foodorder.foodservice.exception.custom.FoodNotFoundException;
import com.foodorder.foodservice.model.Food;
import com.foodorder.foodservice.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<FoodDto> getAllFood() {
        return foodRepository.findAll()
                .stream()
                .map(this::convert).collect(Collectors.toList());
    }

    public FoodDto getFoodByName(String name) {
        return convert(foodRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new FoodNotFoundException(name)));
    }

    public Set<FoodDto> findFoodByName(String name) {
        return foodRepository.findFoodsByNameContainsIgnoreCase(name)
                .stream()
                .map(this::convert).collect(Collectors.toSet());
    }

    private FoodDto convert(Food food) {
        return FoodDto.builder()
                .name(food.getName())
                .description(food.getDescription())
                .price(food.getPrice())
                .build();
    }
}
