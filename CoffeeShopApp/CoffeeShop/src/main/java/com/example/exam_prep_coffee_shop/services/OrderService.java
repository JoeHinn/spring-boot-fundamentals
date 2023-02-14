package com.example.exam_prep_coffee_shop.services;

import com.example.exam_prep_coffee_shop.models.service.OrderServiceModel;
import com.example.exam_prep_coffee_shop.models.views.OrderViewModel;

import java.util.List;

public interface OrderService {

    OrderServiceModel addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findOrdersByPriceDesc();

    void readyOrder(Long id);
}
