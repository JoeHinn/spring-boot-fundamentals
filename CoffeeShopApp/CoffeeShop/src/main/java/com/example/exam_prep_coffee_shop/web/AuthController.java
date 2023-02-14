package com.example.exam_prep_coffee_shop.web;

import com.example.exam_prep_coffee_shop.models.binding.UserLoginDto;
import com.example.exam_prep_coffee_shop.models.binding.UserRegisterDto;
import com.example.exam_prep_coffee_shop.models.service.UserServiceModel;
import com.example.exam_prep_coffee_shop.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class AuthController {
    private final AuthService authService;
    private final ModelMapper modelMapper;

    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterDto userRegisterBindingModel() {
        return new UserRegisterDto();
    }


    @GetMapping("/register")
    private String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDto userRegisterDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()
                || !userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            redirectAttributes
                    .addFlashAttribute("userRegisterDto", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);
            return "redirect:register";
        }

        UserServiceModel userServiceModel
                = this.authService.registerUser(this.modelMapper.map(userRegisterDto, UserServiceModel.class));
        return "redirect:login";
    }

    @ModelAttribute
    public UserLoginDto userLoginBindingModel() {
        return new UserLoginDto();
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userFound")) {
            model.addAttribute("userFound", true);
        }
        return "login";
    }


    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDto userLoginDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginDto", userLoginDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = this.authService.
                findUserByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());

        //Check if user exists
        if (userServiceModel == null) {
            redirectAttributes
                    .addFlashAttribute("userLoginDto", userLoginDto)
                    .addFlashAttribute("userFound", false);
            return "redirect:login";
        }

        this.authService.loginUser(userServiceModel);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
}
