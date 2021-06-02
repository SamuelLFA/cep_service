package com.samuellima.cep.errors;

public class AddressNotFoundException extends IllegalArgumentException {

    public AddressNotFoundException(String message) {
        super(message);
    }
}
