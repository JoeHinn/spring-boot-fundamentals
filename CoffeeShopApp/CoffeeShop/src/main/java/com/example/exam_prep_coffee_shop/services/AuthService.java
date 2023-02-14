package com.example.exam_prep_coffee_shop.services;

import com.example.exam_prep_coffee_shop.models.entities.User;
import com.example.exam_prep_coffee_shop.models.service.UserServiceModel;
import com.example.exam_prep_coffee_shop.models.views.UserViewModel;

import java.util.List;

public interface AuthService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(UserServiceModel userServiceModel);

    void logoutUser();

    User findById(Long id);

    List<UserViewModel> findAllUsersAndCountOfTheirOrdersOrderByOrdersCount();
}

