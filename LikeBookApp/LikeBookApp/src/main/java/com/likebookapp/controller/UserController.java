package com.likebookapp.controller;

import com.likebookapp.model.dto.LoginDto;
import com.likebookapp.model.dto.RegistrationDto;
import com.likebookapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.likebookapp.controller.constants.Constants.BINDING_RESULT_PATH;

@Controller
public class UserController{


    private final AuthService authService;

    @Autowired
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("registrationDTO")
    public RegistrationDto initRegistrationDTO() {
        return new RegistrationDto();
    }

    @ModelAttribute("loginDTO")
    public LoginDto initLoginDTO() {
        return new LoginDto();
    }

    @GetMapping("/register")
    public String getRegister(){
        if (this.authService.isLoggedIn()){
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegistrationDto registrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors() || !this.authService.register(registrationDTO)) {
            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PATH +"registrationDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PATH + "loginDTO", bindingResult);

            return "redirect:/login";
        }

        if (!this.authService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        this.authService.logout();

        return "redirect:/";
    }

}
