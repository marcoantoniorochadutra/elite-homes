package com.elitehomes.domain.converter;

import com.elitehomes.domain.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;

import com.elitehomes.domain.entity.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressConverter extends AbstractJsonConverter<Address> {

    @Autowired
    public AddressConverter(ObjectMapper objectMapper) {
        super(Address.class, objectMapper);
    }
}
