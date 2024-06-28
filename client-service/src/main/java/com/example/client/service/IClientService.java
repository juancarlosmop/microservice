package com.example.client.service;


import com.example.client.model.dto.ClientDTO;
import com.example.client.model.dto.response.GeneralResponseDTO;

public interface IClientService {
    public GeneralResponseDTO getAllClients();
    public GeneralResponseDTO getClientById(Long id);
    public GeneralResponseDTO saveClient(ClientDTO client);
    public GeneralResponseDTO updateClient(Long id, ClientDTO client);
    public GeneralResponseDTO deleteClient(Long id);
}
