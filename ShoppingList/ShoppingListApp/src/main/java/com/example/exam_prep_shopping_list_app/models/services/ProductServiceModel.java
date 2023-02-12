package com.example.exam_prep_shopping_list_app.models.services;

import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductServiceModel {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime neededBefore;
    private CategoryEnum category;
}
