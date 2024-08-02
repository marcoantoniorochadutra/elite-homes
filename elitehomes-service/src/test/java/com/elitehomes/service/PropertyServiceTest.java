package com.elitehomes.service;

import com.elitehomes.model.PropertyDto;
import com.elitehomes.model.base.SelectableDto;
import com.elitehomes.support.AbstractTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PropertyServiceTest extends AbstractTestSupport {

    @Autowired
    private PropertyService propertyService;

    @Test
    public void shouldCreateProperty() {

        PropertyDto dto = PropertyDto.builder()
                .numBedroom(2)
                .numBathroom(2)
                .numSuite(1)
                .parkingSpaces(2)
                .goal(new SelectableDto("RENT"))
                .type(new SelectableDto("RESIDENTIAL"))
                .value(2500.0)
                .details("description")
                .valueDetails("value description")
                .build();


        propertyService.create(dto, null);

    }

}
