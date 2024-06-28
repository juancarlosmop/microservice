package com.example.product.service;


import com.example.product.constants.StatusCodeEnum;
import com.example.product.mapper.ProductMapper;
import com.example.product.model.dto.ProductDTO;
import com.example.product.model.dto.response.GeneralResponseDTO;
import com.example.product.model.entity.ProductEntity;
import com.example.product.repository.ProductRepository;
import com.example.product.util.FormatterUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public GeneralResponseDTO getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        if(products!=null && !products.isEmpty()){
            return GeneralResponseDTO
                    .builder()
                    .data(ProductMapper.productEntityListToDtoList(products))
                    .dateTime(FormatterUtility.getDatetime())
                    .code(StatusCodeEnum.R_001.getDescription())
                    .message("DATA SUCCESS")
                    .build();
        }else{
            return GeneralResponseDTO
                    .builder()
                    .data(Collections.emptyList())
                    .dateTime(FormatterUtility.getDatetime())
                    .code(StatusCodeEnum.R_002.getDescription())
                    .message("DATA SUCCESS")
                    .build();
        }
    }

    @Override
    public GeneralResponseDTO getProductById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if(product.isPresent()){
            return GeneralResponseDTO
                    .builder()
                    .data(ProductMapper.productEntityToDto(product.get()))
                    .dateTime(FormatterUtility.getDatetime())
                    .code(StatusCodeEnum.R_001.getDescription())
                    .message("DATA SUCESS")
                    .build();
        }else{
            return GeneralResponseDTO
                    .builder()
                    .data(Collections.emptyList())
                    .dateTime(FormatterUtility.getDatetime())
                    .code(StatusCodeEnum.R_004.getDescription())
                    .message("NOT DATA")
                    .build();
        }

    }

    @Override
    public GeneralResponseDTO saveProduct(ProductDTO product) {
        productRepository.save( ProductMapper.productDtoToEntity(product));
        return GeneralResponseDTO
                .builder()
                .data(Collections.emptyList())
                .dateTime(FormatterUtility.getDatetime())
                .code(StatusCodeEnum.R_001.getDescription())
                .message("Product was created successfully")
                .build();
    }

    @Override
    public GeneralResponseDTO updateProduct(Long id, ProductDTO product) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            ProductEntity productEntity = ProductMapper.productDtoToEntity(product);
            productEntity.setId(id);
            productRepository.save(productEntity);

            return GeneralResponseDTO
                    .builder()
                    .data(Collections.emptyList())
                    .dateTime(FormatterUtility.getDatetime())
                    .code(StatusCodeEnum.R_001.getDescription())
                    .message("Product was updated successfully")
                    .build();

        }else{
            return GeneralResponseDTO
                    .builder()
                    .data(Collections.emptyList())
                    .dateTime(FormatterUtility.getDatetime())
                    .code(StatusCodeEnum.R_002.name())
                    .message("Product was not updated")
                    .build();
        }

    }

    @Override
    public GeneralResponseDTO deleteProduct(Long id) {
        productRepository.deleteById(id);
        return GeneralResponseDTO
                .builder()
                .data(Collections.emptyList())
                .dateTime(FormatterUtility.getDatetime())
                .code(StatusCodeEnum.R_001.name())
                .message("Product was deleted successfully")
                .build();
    }
}
