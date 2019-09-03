package com.foodorder.foodservice.exception.custom;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String name) {
        super("Such category " + name + " was not found");
    }
}
