package com.samuellima.cep.services;

import com.samuellima.cep.entities.AddressDto;
import com.samuellima.cep.errors.AddressNotFoundException;
import com.samuellima.cep.providers.Provider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindValidAddressServiceTest {

    @Mock
    private Provider provider;

    @InjectMocks
    private FindValidAddressService findValidAddressService;

    @Test
    void whenCEPIsValidAndAddressExistsReturnAddress() {
        AddressDto addressMocked = new AddressDto("Rua teste", "Bairro teste", "Cidade teste", "Uf teste");
        when(provider.getAddress(anyString())).thenReturn(addressMocked);

        var address = findValidAddressService.find("00000000");
        assertEquals(address.getLogradouro(), addressMocked.getLogradouro());
        assertEquals(address.getBairro(), addressMocked.getBairro());
        assertEquals(address.getCidade(), addressMocked.getCidade());
        assertEquals(address.getUf(), addressMocked.getUf());
    }

    @Test
    void whenCEPIsValidAndAddressNonExistsReturnAddress() {
        AddressDto addressMocked = new AddressDto("Rua teste", "Bairro teste", "Cidade teste", "Uf teste");
        when(provider.getAddress("99999999")).thenThrow(AddressNotFoundException.class);
        when(provider.getAddress("99999990")).thenReturn(addressMocked);

        var address = findValidAddressService.find("99999999");
        assertEquals(address.getLogradouro(), addressMocked.getLogradouro());
        assertEquals(address.getBairro(), addressMocked.getBairro());
        assertEquals(address.getCidade(), addressMocked.getCidade());
        assertEquals(address.getUf(), addressMocked.getUf());
    }
}