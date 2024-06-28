package com.example.order.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private int  amount;
    @JsonProperty("date_order")
    private Date dateOrder;
    private ClientDTO client;
    private ProductDTO product;
}
