package com.foodorder.foodservice.exception.custom;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FoodNotFoundException extends RuntimeException {

    public FoodNotFoundException(String name) {
        super("Such food " + name + " was not found");
    }
}
