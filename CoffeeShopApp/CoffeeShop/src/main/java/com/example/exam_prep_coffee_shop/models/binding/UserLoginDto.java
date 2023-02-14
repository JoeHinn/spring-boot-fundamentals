package com.example.exam_prep_coffee_shop.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters long!")
    private String username;

    @NotBlank
    @Size(min = 3, message = "Password must be more than 3 characters long!")
    private String password;
}
