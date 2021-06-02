package com.samuellima.cep.providers;

import com.samuellima.cep.entities.AddressDto;
import com.samuellima.cep.entities.ViaCepResponseDto;
import com.samuellima.cep.errors.AddressNotFoundException;
import com.samuellima.cep.services.SendHttpRequestService;
import org.springframework.beans.factory.annotation.Value;

public class ViaCep implements Provider {

    @Value("${viacep.url}")
    private String baseUrl;

    private SendHttpRequestService<ViaCepResponseDto> sendHttpRequestService;

    public ViaCep(SendHttpRequestService sendHttpRequestService) {
        this.sendHttpRequestService = sendHttpRequestService;
    }

    @Override
    public AddressDto getAddress(String cep) {
        var url = new StringBuilder()
                .append(baseUrl)
                .append(cep)
                .append("/json")
                .toString();

        var response = sendHttpRequestService.get(url);

        var viaCepResponseDto = response.getBody();
        var isError = viaCepResponseDto.isErro();
        if (isError) {
            throw new AddressNotFoundException("Address not found");
        }

        return viaCepResponseDto.toAddress();
    }
}
