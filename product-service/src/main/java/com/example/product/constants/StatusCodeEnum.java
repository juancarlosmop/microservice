package com.example.product.constants;

import lombok.Getter;
public enum StatusCodeEnum {
    R_001("Success"),
    R_002("No available information"),
    R_003("Error during the process"),
    R_004("User not found");

    @Getter
    private String description;

    private StatusCodeEnum(String description) {
        this.description = description;
    }
}
