package com.example.exam_prep_coffee_shop.web;

import com.example.exam_prep_coffee_shop.models.views.OrderViewModel;
import com.example.exam_prep_coffee_shop.security.CurrentUser;
import com.example.exam_prep_coffee_shop.services.OrderService;
import com.example.exam_prep_coffee_shop.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final AuthService authService;

    @Autowired
    public HomeController(CurrentUser currentUser, OrderService orderService, AuthService authService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.authService = authService;
    }


    @GetMapping()
    public String index(Model model) {
        if (!this.currentUser.isLoggedIn()) {
            return "index";
        }

        List<OrderViewModel> orders = this.orderService.findOrdersByPriceDesc();
        model.addAttribute("orders", orders)
                .addAttribute("totalTime",
                        orders.stream()
                                .map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                                .reduce(Integer::sum)
                                .orElse(0));

        model.addAttribute("employees", this.authService.findAllUsersAndCountOfTheirOrdersOrderByOrdersCount());

        return "home";
    }
}
