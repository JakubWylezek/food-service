package com.foodorder.foodservice;

import java.math.BigDecimal;
import com.foodorder.foodservice.model.Food;
import com.foodorder.foodservice.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FoodServiceApplication {

    @Autowired
    private FoodRepository foodRepository;

//    Populate mongodb
    @Bean
    CommandLineRunner preLoadMongo() {
        return args -> {
            foodRepository.save(new Food("Chicken", "So tasty", new BigDecimal(12.00)));
            foodRepository.save(new Food("Soup", "Very tasty", new BigDecimal(8.25)));
            foodRepository.save(new Food("Potato pancakes", "Mmm", new BigDecimal(4.32)));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(FoodServiceApplication.class, args);
    }

}
