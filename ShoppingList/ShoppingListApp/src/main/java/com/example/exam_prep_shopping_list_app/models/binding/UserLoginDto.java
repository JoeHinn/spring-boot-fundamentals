package com.example.exam_prep_shopping_list_app.models.binding;

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
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters!")
    private String username;

    @NotBlank
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    private String password;
}
