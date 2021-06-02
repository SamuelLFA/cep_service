package com.samuellima.cep.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SendHttpRequestService<T> {

    private RestTemplate restTemplate;
    private final Class<T> type;

    public SendHttpRequestService(Class<T> type) {
        this.type = type;
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<T> get(String url) {
        return this.restTemplate.getForEntity(url, this.type);
    }
}
