package com.elitehomes.service;

import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.entity.RealEstate;
import com.elitehomes.model.AddressDto;
import com.elitehomes.model.ContactDto;
import com.elitehomes.model.RealEstateDto;
import com.elitehomes.core.entity.base.MessageDto;
import com.elitehomes.support.AbstractTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealStateServiceTest extends AbstractTestSupport {

    @Autowired
    private RealEstateService realStateService;

    @Test
    public void shouldCreateRealState() {

        AddressDto address = AddressDto.builder()
                .withCity(new SelectableDto("1"))
                .withNeighborhood("Centro")
                .withStreet("Rua Santos")
                .withNumber("123")
                .withZipCode("88780-000")
                .withComplement("Apartamento 101")
                .withDescription("Perto da praia")
                .build();

        ContactDto contact = ContactDto.builder()
                .withEmail("mockEmail")
                .withPhone("mockPhone")
                .withCellphone("mockCellphone")
                .build();

        RealEstateDto realEstate = RealEstateDto.builder()
                .withName("markRE")
                .withTenantKey("markre")
                .withCompanyRegNumber("mockRegistrationNumber")
                .withCreci("creci-1200")
                .withAddress(address)
                .withContact(contact)
                .build();

        RealEstateDto result = realStateService.create(realEstate, loginDefault());

        assertEquals(realEstate.getCompanyRegNumber(), result.getCompanyRegNumber());
        assertEquals(realEstate.getCreci(), result.getCreci());

        assertEquals("Brasil", result.getAddress().getCountry());
        assertEquals("RO", result.getAddress().getState());
        assertEquals(address.getCity().getKey(), result.getAddress().getCity().getKey());
        assertEquals(address.getNeighborhood(), result.getAddress().getNeighborhood());
        assertEquals(address.getStreet(), result.getAddress().getStreet());
        assertEquals(address.getNumber(), result.getAddress().getNumber());
        assertEquals(address.getZipCode(), result.getAddress().getZipCode());
        assertEquals(address.getComplement(), result.getAddress().getComplement());
        assertEquals(address.getDescription(), result.getAddress().getDescription());

        assertEquals(contact.getEmail(), result.getContact().getEmail());
        assertEquals(contact.getPhone(), result.getContact().getPhone());
        assertEquals(contact.getCellphone(), result.getContact().getCellphone());

        List<String> schemas = realEstateRepository.listSchemas();

		assertEquals("markre", schemas.get(0));
    }

    @Test
    public void shouldUpdateRealState() {
        RealEstate realEstate = buildRealEstate("mockNumb-100", "creci-100", "tKey");
        RealEstateDto toUpdate = modelMapper.map(realEstate, RealEstateDto.class);

        AddressDto address = AddressDto.builder()
                .withCity(new SelectableDto("4792"))
                .withNeighborhood("Centro")
                .withStreet("Rua Dona Aurora")
                .withNumber("456")
                .withZipCode("94100-000")
                .withComplement("Sala 202")
                .withDescription("Próximo à Praça central")
                .build();

        ContactDto contact = ContactDto.builder()
                .withEmail("emailUpdate")
                .withPhone("phoneUpdate")
                .withCellphone("cellphoneUpdate")
                .build();

        toUpdate.setId(100L);
        toUpdate.setCreatedAt(Instant.now().plus(1, DAYS));
        toUpdate.setCompanyRegNumber("mockNumb-200");
        toUpdate.setAddress(address);
        toUpdate.setContact(contact);

        RealEstateDto result = realStateService.update(realEstate.getId(), toUpdate, loginDefault());

        assertEquals(1L, result.getId());

        assertEquals(toUpdate.getCompanyRegNumber(), result.getCompanyRegNumber());
        assertEquals(realEstate.getCreci(), result.getCreci());

        assertEquals("Brasil", result.getAddress().getCountry());
        assertEquals("RS", result.getAddress().getState());
        assertEquals(address.getCity().getKey(), result.getAddress().getCity().getKey());
        assertEquals(address.getNeighborhood(), result.getAddress().getNeighborhood());
        assertEquals(address.getStreet(), result.getAddress().getStreet());
        assertEquals(address.getNumber(), result.getAddress().getNumber());
        assertEquals(address.getZipCode(), result.getAddress().getZipCode());
        assertEquals(address.getComplement(), result.getAddress().getComplement());
        assertEquals(address.getDescription(), result.getAddress().getDescription());

        assertEquals(contact.getEmail(), result.getContact().getEmail());
        assertEquals(contact.getPhone(), result.getContact().getPhone());
        assertEquals(contact.getCellphone(), result.getContact().getCellphone());

    }

    @Test
    public void shouldDeleteData() {
        RealEstate rs = buildRealEstate("regNumber90123", "creci1289", "aKey");

        MessageDto message = realStateService.delete(rs.getId(), loginDefault());

        assertEquals("Record deleted successfully.", message.getMessage());
    }

	@Test
	public void shouldThrowErrorDeleteData() {
		RealEstate rs = buildRealEstate("regNumber90113", "creci19", "aKey");

		realStateService.delete(rs.getId(), loginDefault());

		MessageDto message = realStateService.delete(rs.getId(), loginDefault());

        assertEquals("Server problem, please try again.", message.getMessage());
	}
}

