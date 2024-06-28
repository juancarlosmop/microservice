package com.example.product.service;


import com.example.product.model.dto.ProductDTO;
import com.example.product.model.dto.response.GeneralResponseDTO;

public interface IProductService {
    public GeneralResponseDTO getAllProducts();
    public GeneralResponseDTO getProductById(Long id);
    public GeneralResponseDTO saveProduct(ProductDTO product);
    public GeneralResponseDTO updateProduct(Long id, ProductDTO product);
    public GeneralResponseDTO deleteProduct(Long id);

}
