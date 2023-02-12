package com.example.exam_prep_shopping_list_app.models.entities;

import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private CategoryEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();


    public Category(CategoryEnum name, String description) {
        this.name = name;
        this.description = description;
    }
}

