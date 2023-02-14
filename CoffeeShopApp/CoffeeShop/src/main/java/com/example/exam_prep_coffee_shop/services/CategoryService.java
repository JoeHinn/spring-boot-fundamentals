package com.example.exam_prep_coffee_shop.services;

import com.example.exam_prep_coffee_shop.models.entities.Category;
import com.example.exam_prep_coffee_shop.models.entities.enums.CategoryType;

public interface CategoryService {
    Category findCategoryByCategoryEnum(CategoryType categoryNameEnum);
}
