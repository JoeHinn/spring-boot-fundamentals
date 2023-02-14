package com.example.exam_prep_coffee_shop.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;
    private String firstName;

    @NotBlank
    @Size(min = 5, max = 20)
    private String lastName;

    @Email
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    @NotBlank
    @Size(min = 3)
    private String confirmPassword;
}
