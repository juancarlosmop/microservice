package com.example.product.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    @JsonProperty("id_product")
    private Long idProduct;
    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotBlank(message = "Product description cannot be blank")
    private String description;

    @Positive(message = "The price must be a positive number")
    private double price;

    @PositiveOrZero(message = "Stock must be a positive number or zero")
    private int stock;

    // Constructores, getters y setters
}

