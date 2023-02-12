package com.example.exam_prep_shopping_list_app.init;

import com.example.exam_prep_shopping_list_app.models.entities.Category;
import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
import com.example.exam_prep_shopping_list_app.repositories.CategoryRepository;
import com.example.exam_prep_shopping_list_app.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class CategoryInit{
   private final CategoryRepository categoryRepository;

    public CategoryInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void initializeCategories() {
        if (categoryRepository.count() == 0) {
            Category food = new Category();
            food.setName(CategoryEnum.FOOD);
            food.setDescription("Food related products");

            Category drink = new Category();
            drink.setName(CategoryEnum.DRINK);
            drink.setDescription("Drink related products");

            Category household = new Category();
            household.setName(CategoryEnum.HOUSEHOLD);
            household.setDescription("Household related products");

            Category other = new Category();
            other.setName(CategoryEnum.OTHER);
            other.setDescription("Other related products");

            categoryRepository.saveAll(Arrays.asList(food, drink, household, other));
        }
    }
}
