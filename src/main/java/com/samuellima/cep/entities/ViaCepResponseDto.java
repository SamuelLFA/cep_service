package com.samuellima.cep.entities;

import lombok.Data;

@Data
public class ViaCepResponseDto {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String siafi;
    private boolean erro;

    public AddressDto toAddress() {
        return new AddressDto(logradouro, bairro, localidade, uf);
    }
}
