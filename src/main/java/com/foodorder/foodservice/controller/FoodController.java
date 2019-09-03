package com.foodorder.foodservice.controller;

import com.foodorder.foodservice.dto.FoodDto;
import com.foodorder.foodservice.model.Food;
import com.foodorder.foodservice.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
public class FoodController {

    private FoodService foodService;

    @GetMapping(value = "/foods")
    public ResponseEntity<Page<Food>> getAllFood(@RequestParam(value = "foodName", required = false) String foodName,
                                                 @RequestParam(value = "categoryName", required = false)
                                                         String categoryName,
                                                 @NotNull Pageable pageable) {
        return new ResponseEntity<>(foodService.findFoodByRequestParam(foodName, categoryName, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/foods/{name}")
    public ResponseEntity<FoodDto> getFoodByName(@PathVariable String name) {
        return new ResponseEntity<>(foodService.getFoodByName(name), HttpStatus.OK);
    }

}
