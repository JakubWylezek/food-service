package com.foodorder.foodservice.service;

import com.foodorder.foodservice.dto.FoodDto;
import com.foodorder.foodservice.exception.custom.FoodNotFoundException;
import com.foodorder.foodservice.model.Category;
import com.foodorder.foodservice.model.Food;
import com.foodorder.foodservice.repository.CategoryRepository;
import com.foodorder.foodservice.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    public Set<FoodDto> findFoodByCategoryName(String name) {
        return getListFoodFromCategory(categoryRepository.getByNameIgnoreCase(name))
                .stream()
                .map(this::convert).collect(Collectors.toSet());
    }

    private List<Food> getListFoodFromCategory(Category category) {
        List<Food> foods = new ArrayList<>(category.getFoods());
        category.getSubCategories().forEach(category1 -> foods.addAll(getListFoodFromCategory(category1)));
        return foods;
    }

    private FoodDto convert(Food food) {
        return FoodDto.builder()
                .name(food.getName())
                .description(food.getDescription())
                .price(food.getPrice())
                .build();
    }
}
