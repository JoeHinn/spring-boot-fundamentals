package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.LoginDto;
import com.example.spotifyplaylistapp.model.dto.RegisterDto;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean register(RegisterDto registerDto){
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registerDto.getEmail());
        if (byEmail.isPresent()){
            return false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(registerDto.getUsername());
        if (byUsername.isPresent()){
            return false;
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());

        this.userRepository.saveAndFlush(user);

        return true;
    }

    public boolean login(LoginDto loginDto){
        Optional<User> user = this.userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());

        if (user.isEmpty()){
            return false;
        }

        this.loggedUser.login(user.get());
        return true;
    }

    public void logout(){
        this.loggedUser.logout();
    }

    public boolean isLoggedIn() {
        return this.loggedUser.getId() > 0;
    }

}
