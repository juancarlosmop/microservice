package com.example.order.service;


import com.example.order.model.response.GeneralResponseDTO;
import com.example.order.model.response.RqOrderDTO;

public interface IOrderService {
    public GeneralResponseDTO getAllOrders();
    public GeneralResponseDTO getOrderById(Long id);
    public GeneralResponseDTO saveOrder(RqOrderDTO order);
    public GeneralResponseDTO deleteOrder(Long id);
}
