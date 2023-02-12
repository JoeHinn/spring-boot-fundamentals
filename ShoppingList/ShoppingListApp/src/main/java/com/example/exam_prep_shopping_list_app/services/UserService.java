package com.example.exam_prep_shopping_list_app.services;

import com.example.exam_prep_shopping_list_app.models.binding.UserRegisterDto;
import com.example.exam_prep_shopping_list_app.models.entities.User;
import com.example.exam_prep_shopping_list_app.models.services.UserServiceModel;

public interface UserService {
    boolean registerUser(UserRegisterDto userRegisterDto);

    UserServiceModel findUserNameByUserNameAndPassword(String username, String password);

    User findUserById(Long id);

    void loginUser(UserServiceModel userServiceModel);
}
