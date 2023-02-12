package com.example.exam_prep_shopping_list_app.repositories;

import com.example.exam_prep_shopping_list_app.models.entities.Product;
import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT SUM(p.price) FROM Product p")
    BigDecimal findTotalProductsSum();

    List<Product> findAllByCategory_Name(CategoryEnum categoryName);
}
