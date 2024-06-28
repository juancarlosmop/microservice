package com.example.order.mapper;



import com.example.order.model.dto.ClientDTO;
import com.example.order.model.dto.OrderDTO;
import com.example.order.model.dto.ProductDTO;
import com.example.order.model.response.RqOrderDTO;

public class OrderMapper {
    public static OrderDTO oderFromServicesToDTOS(long id,RqOrderDTO order, ClientDTO client, ProductDTO product){
        return  OrderDTO.builder()
                .id(id)
                .amount(order.getAmount())
                .client(client)
                .product(product)
                .dateOrder(order.getDateOrder())
                .build();
    }
}
