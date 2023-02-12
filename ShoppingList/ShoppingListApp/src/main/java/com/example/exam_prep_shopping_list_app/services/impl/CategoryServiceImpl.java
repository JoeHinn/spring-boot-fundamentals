package com.example.exam_prep_shopping_list_app.services.impl;

import com.example.exam_prep_shopping_list_app.models.entities.Category;
import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
import com.example.exam_prep_shopping_list_app.repositories.CategoryRepository;
import com.example.exam_prep_shopping_list_app.services.CategoryService;
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
    public Category findCategoryByCategoryEnum(CategoryEnum categoryEnum) {
        return this.categoryRepository.findByName(categoryEnum).orElse(null);
    }
}
