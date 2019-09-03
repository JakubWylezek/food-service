package com.foodorder.foodservice.service;

import com.foodorder.foodservice.exception.custom.CategoryNotFoundException;
import com.foodorder.foodservice.model.Category;
import com.foodorder.foodservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<Category> getAllCategoryWhereParentIsNull() {
        return categoryRepository.getAllByParentIsNull();
    }

    public Set<String> getCategoriesNamesByParentCategoryName(String categoryName) {
        Category category = getCategoryByName(categoryName);
        return getListCategoriesFromCategoryByParentCategory(category)
                .stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.getByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));
    }

    private List<Category> getListCategoriesFromCategoryByParentCategory(Category category) {
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        category.getSubCategories()
                .forEach(category1 -> categories.addAll(getListCategoriesFromCategoryByParentCategory(category1)));
        return categories;
    }

}
