package com.example.exam_prep_shopping_list_app.services;

import com.example.exam_prep_shopping_list_app.models.binding.AddProductDto;
import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
import com.example.exam_prep_shopping_list_app.models.views.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(AddProductDto addProductDto);

    BigDecimal getTotalProductsPrice();

    List<ProductViewModel> findProductsByCategoryName(CategoryEnum categoryEnum);

    void buyItem(Long id);

    void buyAllItems();
}
