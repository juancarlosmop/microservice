package com.example.product.mapper;



import com.example.product.model.dto.ProductDTO;
import com.example.product.model.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO productEntityToDto(ProductEntity productEntity) {
        return ProductDTO.builder()
                .idProduct(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .build();
    }

    public static List<ProductDTO> productEntityListToDtoList(List<ProductEntity> products){
        return  products.stream()
                .map( product-> ProductDTO.builder()
                        .idProduct(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .build()

                ).collect(Collectors.toList());
    }
    public static  ProductEntity productDtoToEntity(ProductDTO product) {
        return ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

}
