package com.example.exam_prep_shopping_list_app.services.impl;

import com.example.exam_prep_shopping_list_app.models.binding.UserRegisterDto;
import com.example.exam_prep_shopping_list_app.models.entities.User;
import com.example.exam_prep_shopping_list_app.models.services.UserServiceModel;
import com.example.exam_prep_shopping_list_app.repositories.UserRepository;
import com.example.exam_prep_shopping_list_app.security.CurrentUser;
import com.example.exam_prep_shopping_list_app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean registerUser(UserRegisterDto userRegisterDto) {
        try {
            UserServiceModel userServiceModel = this.modelMapper.map(userRegisterDto, UserServiceModel.class);
            this.userRepository.save(this.modelMapper.map(userServiceModel, User.class));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public UserServiceModel findUserNameByUserNameAndPassword(String username, String password) {
        return this.userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }


    @Override
    public void loginUser(UserServiceModel userServiceModel) {
        this.currentUser.setId(userServiceModel.getId());
        this.currentUser.setUsername(userServiceModel.getUsername());
        this.currentUser.setLoggedIn(true);
    }
}