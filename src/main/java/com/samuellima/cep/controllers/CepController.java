package com.samuellima.cep.controllers;

import com.samuellima.cep.entities.AddressDto;
import com.samuellima.cep.services.FindValidAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Validated
@RestController
public class CepController {

    private FindValidAddressService findValidAddressService;

    @Autowired
    public CepController(FindValidAddressService findValidAddressService) {
        this.findValidAddressService = findValidAddressService;
    }

    @Cacheable(value = "cep")
    @GetMapping(path = {"cep/{id}"})
    public ResponseEntity<AddressDto> show(
            @NotBlank(message = "{cep.not_be_blank}")
            @Size(max = 8, min = 8, message = "{cep.should_have_size_8}")
                    @PathVariable String id) {

        var address = findValidAddressService.find(id);

        if (address == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(address);
    }
}
