package com.example.exam_prep_shopping_list_app.models.binding;

import com.example.exam_prep_shopping_list_app.models.entities.enums.CategoryEnum;
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
public class AddProductDto {
    @NotBlank(message = "Name cannot be empty!")
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    private String name;

    @NotBlank(message = "Description cannot be empty!")
    @Size(min = 5, message = "Description min length must be minimum 5 characters!")
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = ".01", message = "Price must be a positive number!")
    private BigDecimal price;

    @FutureOrPresent(message = "The date cannot be in the past!")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime neededBefore;

    @NotNull(message = "Category cannot be empty!")
    private CategoryEnum category;
}
