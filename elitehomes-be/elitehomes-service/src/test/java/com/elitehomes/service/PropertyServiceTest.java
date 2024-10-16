package com.elitehomes.service;

import com.elitehomes.domain.entity.Property;
import com.elitehomes.domain.ref.PropertyGoal;
import com.elitehomes.domain.ref.PropertyType;
import com.elitehomes.model.AddressDto;
import com.elitehomes.model.PropertyDto;
import com.elitehomes.core.entity.base.MessageDto;
import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.support.AbstractTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PropertyServiceTest extends AbstractTestSupport {

    @Autowired
    private PropertyService propertyService;

    @Test
    public void shouldCreateProperty() {
        AddressDto address = AddressDto.builder()
                .withCity(new SelectableDto("1"))
                .withNeighborhood("Centro")
                .withStreet("Rua Santos")
                .withNumber("123")
                .withZipCode("88780-000")
                .withComplement("Apartamento 101")
                .withDescription("Perto da praia")
                .build();

        PropertyDto dto = PropertyDto.builder()
                .withNumBedroom(2)
                .withNumBathroom(2)
                .withNumSuite(1)
                .withParkingSpaces(2)
                .withGoal(new SelectableDto("RENT"))
                .withType(new SelectableDto("RESIDENTIAL"))
                .withOwner(new SelectableDto("1"))
                .withPrice(2500.0)
                .withDescription("description")
                .withTitle("Property Title")
                .withValueDescription("value description")
                .withAddress(address)
                .build();

        PropertyDto result = propertyService.create(dto, loginDefault());

        assertEquals(2, result.getNumBedroom());
        assertEquals(2, result.getNumBathroom());
        assertEquals(1, result.getNumSuite());
        assertEquals(2, result.getParkingSpaces());
        assertSelectable("RENT", result.getGoal());
        assertSelectable("RESIDENTIAL", result.getType());
        assertSelectable("1", result.getOwner());
        assertEquals(2500.0, result.getPrice());
        assertEquals("description", result.getDescription());
        assertEquals("value description", result.getValueDescription());

        assertEquals(address.getCountry(), result.getAddress().getCountry());
        assertEquals("RO", result.getAddress().getState());
        assertEquals(address.getCity().getKey(), result.getAddress().getCity().getKey());
        assertEquals(address.getNeighborhood(), result.getAddress().getNeighborhood());
        assertEquals(address.getStreet(), result.getAddress().getStreet());
        assertEquals(address.getNumber(), result.getAddress().getNumber());
        assertEquals(address.getZipCode(), result.getAddress().getZipCode());
        assertEquals(address.getComplement(), result.getAddress().getComplement());
        assertEquals(address.getDescription(), result.getAddress().getDescription());

    }

    @Test
    public void shouldUpdateProperty() {
        Property property = buildProperty("description", PropertyGoal.RENT, PropertyType.RESIDENTIAL);
        PropertyDto toUpdate = modelMapper.map(property, PropertyDto.class);

        AddressDto address = AddressDto.builder()
                .withCity(new SelectableDto("4792"))
                .withNeighborhood("Centro")
                .withStreet("Rua Dona Aurora")
                .withNumber("456")
                .withZipCode("94100-000")
                .withComplement("Sala 202")
                .withDescription("Próximo à Praça central")
                .build();

        toUpdate.setAddress(address);
        toUpdate.setId(100L);
        toUpdate.setCreatedAt(Instant.now().plus(1, DAYS));
        toUpdate.setNumBathroom(15);
        toUpdate.setNumSuite(15);
        toUpdate.setNumBathroom(15);
        toUpdate.setPrice(520.0);

        PropertyDto result = propertyService.update(property.getId(), toUpdate, loginDefault());

        assertEquals(15, result.getNumBathroom());
        assertEquals(15, result.getNumSuite());
        assertEquals(15, result.getNumBathroom());
        assertEquals(520.0, result.getPrice());
        assertEquals(property.getId(), result.getId());

        assertEquals("Brasil", result.getAddress().getCountry());
        assertEquals("RS", result.getAddress().getState());
        assertEquals("Gravataí", result.getAddress().getCity().getValue());
        assertEquals("Centro", result.getAddress().getNeighborhood());
        assertEquals("Rua Dona Aurora", result.getAddress().getStreet());
        assertEquals("456", result.getAddress().getNumber());
        assertEquals("94100-000", result.getAddress().getZipCode());
        assertEquals("Sala 202", result.getAddress().getComplement());
        assertEquals("Próximo à Praça central", result.getAddress().getDescription());

    }

    @Test
    public void shouldDeleteData() {
        Property rs = buildProperty("regNumber90123", PropertyGoal.RENT, PropertyType.RESIDENTIAL);

        MessageDto message = propertyService.delete(rs.getId(), loginDefault());

        assertEquals("Record deleted successfully.", message.getMessage());
    }

    @Test
    public void shouldThrowErrorDeleteData() {
        Property rs = buildProperty("regNumber90123", PropertyGoal.RENT, PropertyType.RESIDENTIAL);

        propertyService.delete(rs.getId(), loginDefault());

        MessageDto message = propertyService.delete(rs.getId(), loginDefault());

        assertEquals("Server problem, please try again.", message.getMessage());
    }

}
