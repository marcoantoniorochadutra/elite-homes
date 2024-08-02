package com.elitehomes.service;

import com.elitehomes.model.AddressDto;
import com.elitehomes.model.ContactDto;
import com.elitehomes.model.RealEstateDto;
import com.elitehomes.support.AbstractTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealStateServiceTest extends AbstractTestSupport {

    @Autowired
    private RealEstateService realStateService;

    @Test
    public void shouldCreateRealState() {
        AddressDto address = AddressDto.builder()
                .withCountry("Brasil")
                .withState("Santa Catarina")
                .withCity("Imbituba")
                .withNeighborhood("Centro")
                .withStreet("Rua Santos")
                .withNumber("123")
                .withZipCode("88780-000")
                .withComplement("Apartamento 101")
                .withDetails("Perto da praia")
                .build();

        ContactDto contact = ContactDto.builder()
                .withEmail("mockEmail")
                .withPhone("mockPhone")
                .withCellphone("mockCellphone")
                .build();

        RealEstateDto realEstate = RealEstateDto.builder()
                .withCompanyRegNumber("mockRegistrationNumber")
                .withCreci("mockCreci")
                .withAddress(address)
                .withContact(contact)
                .build();

        RealEstateDto result = realStateService.create(realEstate, loginDefault());

        assertEquals(realEstate.getCompanyRegNumber(), result.getCompanyRegNumber());
        assertEquals(realEstate.getCreci(), result.getCreci());

        assertEquals(address.getCountry(), result.getAddress().getCountry());
        assertEquals(address.getState(), result.getAddress().getState());
        assertEquals(address.getCity(), result.getAddress().getCity());
        assertEquals(address.getNeighborhood(), result.getAddress().getNeighborhood());
        assertEquals(address.getStreet(), result.getAddress().getStreet());
        assertEquals(address.getNumber(), result.getAddress().getNumber());
        assertEquals(address.getZipCode(), result.getAddress().getZipCode());
        assertEquals(address.getComplement(), result.getAddress().getComplement());
        assertEquals(address.getDetails(), result.getAddress().getDetails());

        assertEquals(contact.getEmail(), result.getContact().getEmail());
        assertEquals(contact.getPhone(), result.getContact().getPhone());
        assertEquals(contact.getCellphone(), result.getContact().getCellphone());

    }
}

