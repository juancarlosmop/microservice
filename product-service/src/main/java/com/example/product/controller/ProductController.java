package com.example.product.controller;


import com.example.product.model.dto.ProductDTO;
import com.example.product.model.dto.response.GeneralResponseDTO;
import com.example.product.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Operation(
            summary = "Get All Products REST API",
            description = "Get All Products REST API is used to get a all the products from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<GeneralResponseDTO> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @Operation(
            summary = "Get Product By ID REST API",
            description = "Get Product By ID REST API is used to get a single product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));

    }

    @Operation(
            summary = "Create Product REST API",
            description = "Create Product REST API is used to save product in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<GeneralResponseDTO> createProduct(@RequestBody @Valid ProductDTO product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @Operation(
            summary = "Update Product REST API",
            description = "Update Product REST API is used to update a particular product in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO product) {
        return ResponseEntity.ok( productService.updateProduct(id, product));

    }


    @Operation(
            summary = "Delete Product REST API",
            description = "Delete Product REST API is used to delete a particular product from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }


}
