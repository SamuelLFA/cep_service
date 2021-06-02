package com.samuellima.cep.controllers;

import com.samuellima.cep.entities.AddressDto;
import com.samuellima.cep.services.FindValidAddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CepControllerTest {

    @Mock
    FindValidAddressService findValidAddressService;

    @Test
    void whenCepIsValidReturnSuccess() {
        var address = new AddressDto("logradouro teste", "bairro teste", "cidade  teste", "uf teste");
        when(findValidAddressService.find("12345678")).thenReturn(address);
        var cepController = new CepController(findValidAddressService);

        var httpResponse = cepController.show("12345678");
        var addressReturned = httpResponse.getBody();

        assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(address.getLogradouro(), addressReturned.getLogradouro());
        assertEquals(address.getBairro(), addressReturned.getBairro());
        assertEquals(address.getCidade(), addressReturned.getCidade());
        assertEquals(address.getUf(), addressReturned.getUf());
    }

    @Test
    void whenCepNonExistsAtAllReturnNotFound() {
        when(findValidAddressService.find("00000000")).thenReturn(null);
        var cepController = new CepController(findValidAddressService);

        var httpResponse = cepController.show("00000000");

        assertEquals(httpResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}