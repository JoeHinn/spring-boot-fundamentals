package com.example.exam_prep_shopping_list_app.web;

import com.example.exam_prep_shopping_list_app.models.entities.Category;
import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
import com.example.exam_prep_shopping_list_app.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String indexPage(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }
        model.addAttribute("totalSum", this.productService.getTotalProductsPrice());
        model.addAttribute("foods", this.productService.findProductsByCategoryName(CategoryEnum.FOOD));
        model.addAttribute("drinks", this.productService.findProductsByCategoryName(CategoryEnum.DRINK));
        model.addAttribute("households", this.productService.findProductsByCategoryName(CategoryEnum.HOUSEHOLD));
        model.addAttribute("others", this.productService.findProductsByCategoryName(CategoryEnum.OTHER));
        return "home";
    }
}
