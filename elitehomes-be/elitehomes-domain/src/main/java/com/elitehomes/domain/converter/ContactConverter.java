package com.elitehomes.domain.converter;

import com.elitehomes.domain.entity.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactConverter extends AbstractJsonConverter<Contact> {

    @Autowired
    public ContactConverter(ObjectMapper objectMapper) {
        super(Contact.class, objectMapper);
    }
}
