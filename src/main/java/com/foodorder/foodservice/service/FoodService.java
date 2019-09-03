package com.foodorder.foodservice.service;

import com.foodorder.foodservice.dto.FoodDto;
import com.foodorder.foodservice.exception.custom.FoodNotFoundException;
import com.foodorder.foodservice.model.Food;
import com.foodorder.foodservice.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class FoodService {

    private FoodRepository foodRepository;
    private CategoryService categoryService;

    public Page<Food> getAllFood(Pageable page) {
        return foodRepository.findAll(page);
    }

    public FoodDto getFoodByName(String foodName) {
        return convert(foodRepository.findByNameIgnoreCase(foodName)
                .orElseThrow(() -> new FoodNotFoundException(foodName)));
    }

    public Page<Food> findFoodByRequestParam(String foodName, String categoryName, Pageable pageable) {
        if (foodName != null && categoryName != null) {
            return findFoodByNameAndCategory(foodName, categoryName, pageable);
        } else if (foodName != null) {
            return findFoodByName(foodName, pageable);
        } else if (categoryName != null) {
            return findFoodByCategory(categoryName, pageable);
        }
        return getAllFood(pageable);
    }

    public Page<Food> findFoodByName(String foodName, Pageable pageable) {
        return foodRepository.findByNameIsContainingIgnoreCase(foodName, pageable);
    }

    public Page<Food> findFoodByCategory(String categoryName, Pageable pageable) {
        Set<String> categoryNames = categoryService.getCategoriesNamesByParentCategoryName(categoryName);
        return foodRepository.findByCategory_NameIn(categoryNames, pageable);
    }

    public Page<Food> findFoodByNameAndCategory(String foodName, String categoryName, Pageable pageable) {
        Set<String> categoryNames = categoryService.getCategoriesNamesByParentCategoryName(categoryName);
        return foodRepository.findByNameIsContainingIgnoreCaseAndCategory_NameIn(foodName, categoryNames, pageable);
    }

    private FoodDto convert(Food food) {
        return FoodDto.builder()
                .name(food.getName())
                .description(food.getDescription())
                .price(food.getPrice())
                .build();
    }
}
