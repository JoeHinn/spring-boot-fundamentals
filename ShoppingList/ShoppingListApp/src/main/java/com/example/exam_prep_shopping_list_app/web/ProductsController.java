package com.example.exam_prep_shopping_list_app.web;

import com.example.exam_prep_shopping_list_app.models.binding.AddProductDto;
import com.example.exam_prep_shopping_list_app.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }
        return "product-add";
    }

    @ModelAttribute
    public AddProductDto productAddBindingModel() {
        return new AddProductDto();
    }

    @PostMapping("/add")
    public String addProductConfirm(@Valid AddProductDto addProductDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", addProductDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add";
        }

        this.productService.addProduct(addProductDto);

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buyItem(@PathVariable Long id, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }
        this.productService.buyItem(id);
        return "redirect:/";
    }

    @GetMapping("buy/all")
    public String buyAllItems(HttpSession httpSession){
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }
        this.productService.buyAllItems();
        return "redirect:/";
    }

}
