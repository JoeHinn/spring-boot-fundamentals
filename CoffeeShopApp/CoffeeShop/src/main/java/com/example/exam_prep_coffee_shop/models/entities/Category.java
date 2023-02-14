package com.example.exam_prep_coffee_shop.models.entities;

import com.example.exam_prep_coffee_shop.models.entities.enums.CategoryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CategoryType name;

    @Column(name = "needed_time",nullable = false)
    private Integer neededTime;

    public Category(CategoryType name, Integer neededTime) {
        this.name = name;
        this.neededTime = neededTime;
    }
}
