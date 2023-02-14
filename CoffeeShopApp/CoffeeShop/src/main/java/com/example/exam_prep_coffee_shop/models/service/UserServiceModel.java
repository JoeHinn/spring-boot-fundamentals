package com.example.exam_prep_coffee_shop.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

}
