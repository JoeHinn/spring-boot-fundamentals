package com.example.exam_prep_coffee_shop.init;

import com.example.exam_prep_coffee_shop.models.entities.Category;
import com.example.exam_prep_coffee_shop.models.entities.enums.CategoryType;
import com.example.exam_prep_coffee_shop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            List<Category> categories = Arrays.asList(
                    new Category(CategoryType.COFFEE, 2),
                    new Category(CategoryType.CAKE, 10),
                    new Category(CategoryType.DRINK, 1),
                    new Category(CategoryType.OTHER, 5)
            );
            categoryRepository.saveAll(categories);
        }
    }
}
