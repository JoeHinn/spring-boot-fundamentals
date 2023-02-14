package com.example.exam_prep_coffee_shop.models.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserViewModel {

    private String username;
    private Integer ordersCount;
}
