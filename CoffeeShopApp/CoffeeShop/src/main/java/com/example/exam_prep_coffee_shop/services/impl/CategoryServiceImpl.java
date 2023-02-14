package com.example.exam_prep_coffee_shop.services.impl;

import com.example.exam_prep_coffee_shop.models.entities.Category;
import com.example.exam_prep_coffee_shop.models.entities.enums.CategoryType;
import com.example.exam_prep_coffee_shop.repositories.CategoryRepository;
import com.example.exam_prep_coffee_shop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByCategoryEnum(CategoryType categoryNameEnum) {
        return this.categoryRepository.findByName(categoryNameEnum).orElse(null);
    }
}
