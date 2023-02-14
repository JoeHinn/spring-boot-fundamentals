package com.example.exam_prep_coffee_shop.services.impl;

import com.example.exam_prep_coffee_shop.models.entities.Order;
import com.example.exam_prep_coffee_shop.models.service.OrderServiceModel;
import com.example.exam_prep_coffee_shop.models.views.OrderViewModel;
import com.example.exam_prep_coffee_shop.repositories.OrderRepository;
import com.example.exam_prep_coffee_shop.security.CurrentUser;
import com.example.exam_prep_coffee_shop.services.CategoryService;
import com.example.exam_prep_coffee_shop.services.OrderService;
import com.example.exam_prep_coffee_shop.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final AuthService authService;
    private final CategoryService categoryService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, CurrentUser currentUser, AuthService authService, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.authService = authService;
        this.categoryService = categoryService;
    }

    @Override
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) {
        Order order = this.modelMapper.map(orderServiceModel, Order.class);
        order.setEmployee(this.authService.findById(this.currentUser.getId()))
                .setCategory(this.categoryService.findCategoryByCategoryEnum(orderServiceModel.getCategory()));

        return this.modelMapper.map(this.orderRepository.save(order), OrderServiceModel.class);
    }

    @Override
    public List<OrderViewModel> findOrdersByPriceDesc() {
        return this.orderRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(order -> this.modelMapper.map(order, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void readyOrder(Long id) {
       this.orderRepository.deleteById(id);
    }
}
