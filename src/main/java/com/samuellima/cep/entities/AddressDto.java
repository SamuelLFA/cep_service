package com.samuellima.cep.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
}
