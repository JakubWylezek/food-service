package com.foodorder.foodservice.repository;

import com.foodorder.foodservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> getAllByParentIsNull();

    Optional<Category> getByNameIgnoreCase(String name);
}
