package com.foodorder.foodservice.service;

import com.foodorder.foodservice.model.Category;
import com.foodorder.foodservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategoryWhereParentIsNull() {
        return categoryRepository.getAllByParentIsNull();
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.getByNameIgnoreCase(name);
    }

}
