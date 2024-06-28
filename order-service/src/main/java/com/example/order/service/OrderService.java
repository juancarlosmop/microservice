package com.example.order.service;


import com.example.order.constants.AppConstants;
import com.example.order.constants.StatusCodeEnum;
import com.example.order.mapper.OrderMapper;
import com.example.order.model.dto.ClientDTO;
import com.example.order.model.dto.OrderDTO;
import com.example.order.model.dto.ProductDTO;
import com.example.order.model.response.GeneralResponseDTO;
import com.example.order.model.response.RqOrderDTO;
import com.example.order.util.FormatterUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrderService  implements IOrderService{

    private final List<OrderDTO> listOrder = new ArrayList<>();
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public GeneralResponseDTO getAllOrders() {
        return GeneralResponseDTO
                .builder()
                .data(listOrder)
                .dateTime(FormatterUtility.getDatetime())
                .code(StatusCodeEnum.R_001.getDescription())
                .message("DATA SUCCESS")
                .build();
    }

    @Override
    public GeneralResponseDTO getOrderById(Long id) {
        OrderDTO order=  listOrder.stream()
                .filter(x-> x.getId().equals(id))
                .findFirst()
                .orElse(null);
        if(order!=null){
            return GeneralResponseDTO
                    .builder()
                    .data(order)
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
                    .message("NOT DATA")
                    .build();
        }
    }

    @Override
    public GeneralResponseDTO saveOrder(RqOrderDTO order) {
        ClientDTO findClient = getClientFromService(order.getIdClient());
        ProductDTO findProduct = getProductFromService(order.getIdProduct());
        long lastId= 0L;
        if(!listOrder.isEmpty()) {
            long findId = listOrder.stream()
                    .map(OrderDTO::getId)
                    .max(Comparator.naturalOrder())
                    .orElse(null);
            lastId =findId+1;
        }else{
            lastId=1L;
        }
        listOrder.add( OrderMapper.oderFromServicesToDTOS(lastId,order, findClient, findProduct) );
        //orderRepository.save();
        return GeneralResponseDTO
                .builder()
                .data(Collections.emptyList())
                .dateTime(FormatterUtility.getDatetime())
                .code(StatusCodeEnum.R_001.getDescription())
                .message("Order was created successfully")
                .build();
    }

    private ClientDTO getClientFromService(Long idClient) {
        String url = AppConstants.URL_CLIENT + idClient;
        ClientDTO clientDTO = null;
        try {
            ResponseEntity<GeneralResponseDTO<Map<String, Object>>> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<GeneralResponseDTO<Map<String, Object>>>() {
                    });
            // Verificar si la respuesta no es nula y contiene datos
            if (responseEntity != null && responseEntity.getBody() != null) {
                // Obtener el cuerpo de la respuesta
                GeneralResponseDTO<Map<String, Object>> response = responseEntity.getBody();

                // Convertir el mapa (data) a ClientDTO usando ObjectMapper
                if (response.getData() != null) {
                    clientDTO = objectMapper.convertValue(response.getData(), ClientDTO.class);
                    // Aquí puedes trabajar con clientDTO según tus necesidades
                }
            } else {
                return clientDTO;
            }
            return clientDTO;
        } catch (HttpClientErrorException e) {
            return clientDTO;
        } catch (HttpServerErrorException e) {

        } catch (Exception e) {
            return clientDTO;
        }
        return clientDTO;
    }
    private ProductDTO getProductFromService(Long idProduct){
        String url = AppConstants.URL_PRODUCT + idProduct;
        ResponseEntity<GeneralResponseDTO<Map<String, Object>>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<GeneralResponseDTO<Map<String, Object>>>() {});
        ProductDTO productDTO = null;
        if (responseEntity != null && responseEntity.getBody() != null) {
            // Obtener el cuerpo de la respuesta
            GeneralResponseDTO<Map<String, Object>> response = responseEntity.getBody();

            // Convertir el mapa (data) a ClientDTO usando ObjectMapper
            if (response.getData() != null) {
                productDTO = objectMapper.convertValue(response.getData(), ProductDTO.class);
                // Aquí puedes trabajar con clientDTO según tus necesidades
            }
        } else {
            return productDTO;
        }
        return productDTO;

    }
    @Override
    public GeneralResponseDTO deleteOrder(Long id) {
        listOrder.removeIf(order-> order.getId().equals(id));
        return GeneralResponseDTO
                .builder()
                .data(Collections.emptyList())
                .dateTime(FormatterUtility.getDatetime())
                .code(StatusCodeEnum.R_001.getDescription())
                .message("Order was deleted successfully")
                .build();
    }
}
