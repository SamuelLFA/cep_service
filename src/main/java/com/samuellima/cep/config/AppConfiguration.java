package com.samuellima.cep.config;

import com.samuellima.cep.entities.ViaCepResponseDto;
import com.samuellima.cep.providers.Provider;
import com.samuellima.cep.providers.ViaCep;
import com.samuellima.cep.services.SendHttpRequestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Provider provider() {
        SendHttpRequestService<ViaCepResponseDto> sendHttpRequestService = new SendHttpRequestService<>(ViaCepResponseDto.class);
        return new ViaCep(sendHttpRequestService);
    }
}
