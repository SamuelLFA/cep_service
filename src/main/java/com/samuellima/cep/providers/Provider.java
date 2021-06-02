package com.samuellima.cep.providers;

import com.samuellima.cep.entities.AddressDto;

public interface Provider {

    AddressDto getAddress(String cep);
}
