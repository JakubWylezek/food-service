package com.foodorder.foodservice.controller;

import com.foodorder.foodservice.model.Food;
import com.foodorder.foodservice.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping(value = "/foods")
    public ResponseEntity<List<Food>> getAllFood() {
        return new ResponseEntity<>(foodService.getAllFood(), HttpStatus.OK);
    }

    @GetMapping(value = "/foods/{name}")
    public ResponseEntity<Food> getFoodByName(@PathVariable String name) {
        return new ResponseEntity<>(foodService.getFoodByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/foods/find")
    public ResponseEntity<Set<Food>> findFoodByName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(foodService.findFoodByName(name), HttpStatus.OK);
    }

}