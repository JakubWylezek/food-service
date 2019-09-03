package com.foodorder.foodservice.repository;

import com.foodorder.foodservice.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByNameIgnoreCase(String name);

    Page<Food> findByNameIsContainingIgnoreCase(String name, Pageable pageable);

    Page<Food> findByNameIsContainingIgnoreCaseAndCategory_NameIn(String foodName, Set<String> categoryNames,
                                                                  Pageable pageable);

    Page<Food> findByCategory_NameIn(Set<String> categoryNames, Pageable pageable);
}
