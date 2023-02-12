package com.example.exam_prep_shopping_list_app.services.impl;

import com.example.exam_prep_shopping_list_app.models.binding.AddProductDto;
import com.example.exam_prep_shopping_list_app.models.entities.Product;
import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
import com.example.exam_prep_shopping_list_app.models.services.ProductServiceModel;
import com.example.exam_prep_shopping_list_app.models.views.ProductViewModel;
import com.example.exam_prep_shopping_list_app.repositories.ProductRepository;
import com.example.exam_prep_shopping_list_app.services.CategoryService;
import com.example.exam_prep_shopping_list_app.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(AddProductDto addProductDto) {
        ProductServiceModel productServiceModel = this.modelMapper.map(addProductDto, ProductServiceModel.class);
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setCategory(
                this.categoryService
                        .findCategoryByCategoryEnum(productServiceModel.getCategory()));
        this.productRepository.save(product);
    }

    @Override
    public BigDecimal getTotalProductsPrice() {
        return this.productRepository.findTotalProductsSum() != null
                ? this.productRepository.findTotalProductsSum()
                : BigDecimal.ZERO;
    }

    @Override
    public List<ProductViewModel> findProductsByCategoryName(CategoryEnum categoryEnum) {
        return this.productRepository
                .findAllByCategory_Name(categoryEnum)
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyItem(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void buyAllItems() {
        this.productRepository.deleteAll();
    }
}
