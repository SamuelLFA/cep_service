package com.samuellima.cep.providers;

import com.samuellima.cep.entities.ViaCepResponseDto;
import com.samuellima.cep.errors.AddressNotFoundException;
import com.samuellima.cep.services.SendHttpRequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViaCepTest {

    @Mock
    private SendHttpRequestService<ViaCepResponseDto> sendHttpRequestService;

    @Test
    void whenCepIsValidReturnAddress() {
        when(sendHttpRequestService.get(anyString())).thenReturn(ResponseEntity.ok(new ViaCepResponseDto()));
        var provider = new ViaCep(sendHttpRequestService);

        var address = provider.getAddress("3750151");

        assertNotNull(address);
    }

    @Test
    void whenCepIsValidReturn() {
        var response = new ViaCepResponseDto();
        response.setErro(true);
        when(sendHttpRequestService.get(anyString())).thenReturn(ResponseEntity.ok(response));
        var provider = new ViaCep(sendHttpRequestService);

        var exception = assertThrows(AddressNotFoundException.class, () -> provider.getAddress("0000000000"));

        assertEquals(exception.getMessage(), "Address not found");
    }
}