package com.example.client.model.dto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_client")
    private Long idClient;
    private String name;
    private String email;
    @Column(name="cell_phone")
    private String cellPhone;
}
