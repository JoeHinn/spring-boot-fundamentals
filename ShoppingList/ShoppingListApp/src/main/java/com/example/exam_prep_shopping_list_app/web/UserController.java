package com.example.exam_prep_shopping_list_app.web;

import com.example.exam_prep_shopping_list_app.models.binding.UserLoginDto;
import com.example.exam_prep_shopping_list_app.models.binding.UserRegisterDto;
import com.example.exam_prep_shopping_list_app.models.services.UserServiceModel;
import com.example.exam_prep_shopping_list_app.services.UserService;
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
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserRegisterDto userRegisterBindingModel() {
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userAlreadyRegistered", false);
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDto userRegisterDto,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()
                || !userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);
            return "redirect:register";
        }

        boolean isSaved = this.userService.registerUser(userRegisterDto);
        if (!isSaved) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterDto)
                    .addFlashAttribute("userAlreadyRegistered", true);
            return "redirect:register";
        }
        return "redirect:login";
    }

    @ModelAttribute
    public UserLoginDto userLoginBindingModel() {
        return new UserLoginDto();
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userNotFound")) {
            model.addAttribute("userNotFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDto userLoginDto,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult);
            return "redirect:login";
        }

        //CheckIfUserExists
        UserServiceModel userServiceModel = this.userService
                .findUserNameByUserNameAndPassword(userLoginDto.getUsername(),
                        userLoginDto.getPassword());
        if (userServiceModel == null) {
            redirectAttributes
                    .addFlashAttribute("userRegisterLoginBindingModel", userLoginDto)
                    .addFlashAttribute("userNotFound", true);
            return "redirect:login";
        }
        httpSession.setAttribute("user", userServiceModel);
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
