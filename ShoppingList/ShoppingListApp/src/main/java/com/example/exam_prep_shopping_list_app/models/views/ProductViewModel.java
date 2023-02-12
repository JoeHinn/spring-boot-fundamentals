package com.example.exam_prep_shopping_list_app.models.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductViewModel {
    private Long id;
    private String name;
    private BigDecimal price;
}

