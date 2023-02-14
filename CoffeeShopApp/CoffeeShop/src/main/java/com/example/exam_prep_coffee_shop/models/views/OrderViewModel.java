package com.example.exam_prep_coffee_shop.models.views;

import com.example.exam_prep_coffee_shop.models.entities.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class OrderViewModel {
    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime orderTime;
    private Category category;
}
