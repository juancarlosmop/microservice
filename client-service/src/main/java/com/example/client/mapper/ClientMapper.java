package com.example.client.mapper;


import com.example.client.model.dto.entity.ClientEntity;
import com.example.client.model.dto.ClientDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {
    public static ClientDTO ClientEntityToDto(ClientEntity client) {
        return ClientDTO.builder()
                .idClient(client.getIdClient())
                .name(client.getName())
                .email(client.getEmail())
                .cellPhone(client.getCellPhone())
                .build();
    }

    public static List<ClientDTO> clientEntityListTotoList(List<ClientEntity> clients){
        return clients.stream()
                .map(client-> ClientDTO.builder()
                        .idClient(client.getIdClient())
                        .name(client.getName())
                        .email(client.getEmail())
                        .cellPhone(client.getCellPhone())
                        .build()
                 )
                .collect(Collectors.toList());
    }
    public static  ClientEntity clientDtoToEntity(ClientDTO client) {
         return ClientEntity.builder()
                 .idClient(client.getIdClient())
                .name(client.getName())
                .email(client.getEmail())
                .cellPhone(client.getCellPhone())
                .build();
    }
}
