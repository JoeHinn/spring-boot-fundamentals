package com.example.exam_prep_coffee_shop.services.impl;

import com.example.exam_prep_coffee_shop.models.entities.User;
import com.example.exam_prep_coffee_shop.models.service.UserServiceModel;
import com.example.exam_prep_coffee_shop.models.views.UserViewModel;
import com.example.exam_prep_coffee_shop.repositories.UserRepository;
import com.example.exam_prep_coffee_shop.security.CurrentUser;
import com.example.exam_prep_coffee_shop.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        return this.modelMapper.map(this.userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return this.userRepository.
                findByUsernameAndPassword(username, password).
                map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {
        this.currentUser.setLoggedIn(true);
        this.currentUser.setId(userServiceModel.getId());
        this.currentUser.setUsername(userServiceModel.getUsername());
    }

    @Override
    public void logoutUser() {
        this.currentUser.setLoggedIn(false);
        this.currentUser.setId(null);
        this.currentUser.setUsername(null);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserViewModel> findAllUsersAndCountOfTheirOrdersOrderByOrdersCount() {
        return this.userRepository
                .findAllByOrdersCount()
                .stream()
                .map(user -> { UserViewModel userViewModel = new UserViewModel();
                               userViewModel.setUsername(user.getUsername());
                               userViewModel.setOrdersCount(user.getOrders().size());
                               return userViewModel;}).toList();
    }
}
