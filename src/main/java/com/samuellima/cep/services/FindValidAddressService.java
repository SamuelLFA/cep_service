package com.samuellima.cep.services;

import com.samuellima.cep.entities.AddressDto;
import com.samuellima.cep.errors.AddressNotFoundException;
import com.samuellima.cep.providers.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindValidAddressService {

    private Provider provider;

    @Autowired
    public FindValidAddressService(Provider provider) {
        this.provider = provider;
    }

    public AddressDto find(String cep) {
        try {
            return provider.getAddress(cep);
        } catch (AddressNotFoundException exception) {
            var newCepToTry = findPreviousCep(cep);

            return find(newCepToTry);
        }
    }

    private String findPreviousCep(String cep) {
        int positionLastNonZeroDigit = 7;
        while(cep.charAt(positionLastNonZeroDigit) == '0') {
            positionLastNonZeroDigit--;
        }
        return cep.substring(0, positionLastNonZeroDigit) + "0" + cep.substring(positionLastNonZeroDigit + 1);
    }
}
