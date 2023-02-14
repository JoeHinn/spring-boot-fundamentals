package com.example.exam_prep_coffee_shop.web;

import com.example.exam_prep_coffee_shop.models.binding.OrderAddDto;
import com.example.exam_prep_coffee_shop.models.service.OrderServiceModel;
import com.example.exam_prep_coffee_shop.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public OrderAddDto orderAddBindingModel() {
        return new OrderAddDto();
    }

    @GetMapping("/add")
    public String addOrder() {
        return "order-add";
    }


    @PostMapping("/add")
    public String addOrderConfirm(@Valid OrderAddDto OrderAddDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("orderAddDto", OrderAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel",
                            bindingResult);
            return "redirect:add";
        }

        OrderServiceModel orderServiceModel = this.orderService
                .addOrder(this.modelMapper.map(OrderAddDto, OrderServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable Long id){
        this.orderService.readyOrder(id);
        return "redirect:/";
    }

}
