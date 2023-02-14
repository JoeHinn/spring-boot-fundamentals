package com.example.exam_prep_coffee_shop.models.binding;

import com.example.exam_prep_coffee_shop.models.entities.enums.CategoryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderAddDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Positive
    @NotNull
    private BigDecimal price;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;

    @NotNull
    private CategoryType category;

    @Size(min = 5)
    private String description;
}
