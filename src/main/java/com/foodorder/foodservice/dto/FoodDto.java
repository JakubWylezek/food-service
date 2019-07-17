package com.foodorder.foodservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class FoodDto {

    private String name;
    private String description;
    private BigDecimal price;
}
