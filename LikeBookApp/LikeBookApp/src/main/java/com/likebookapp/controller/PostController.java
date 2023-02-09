package com.likebookapp.controller;

import com.likebookapp.model.dto.PostDto;
import com.likebookapp.service.AuthService;
import com.likebookapp.service.PostService;
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
public class PostController {

    private final AuthService authService;
    private final PostService postService;

    @Autowired
    public PostController(AuthService authService, PostService postService) {
        this.authService = authService;
        this.postService = postService;
    }

    @ModelAttribute("postDTO")
    public PostDto initPostAddDTO() {
        return new PostDto();
    }

    @GetMapping("/post/add")
    public String addPost() {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "/post-add";
    }

    @PostMapping("/post/add")
    public String ships(@Valid PostDto postAddDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.postService.create(postAddDTO)) {
            redirectAttributes.addFlashAttribute("postAddDTO", postAddDTO);
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PATH +"postAddDTO", bindingResult);

            return "redirect:/post/add";
        }

        return "redirect:/home";
    }
}
