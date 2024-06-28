package com.example.order.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClientDTO {
    @JsonProperty("id_client")
    private Long idClient;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "email cannot be blank")
    @Email(message = "must be a correct email")
    private String email;
    @JsonProperty("cell_phone")
    @NotBlank(message = "cell phone cannot be blank")
    private String cellPhone;
}
