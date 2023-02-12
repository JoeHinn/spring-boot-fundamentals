package com.example.exam_prep_shopping_list_app.services;

import com.example.exam_prep_shopping_list_app.models.entities.Category;
import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
import com.example.exam_prep_shopping_list_app.repositories.CategoryRepository;

public interface CategoryService {
    Category findCategoryByCategoryEnum(CategoryEnum categoryEnum);
}
