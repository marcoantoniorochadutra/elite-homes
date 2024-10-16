package com.elitehomes.service;

import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.ref.AvailableReference;
import com.elitehomes.service.impl.ReferenceServiceImpl;
import com.elitehomes.support.AbstractTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReferenceServiceTest extends AbstractTestSupport {

    @Autowired
    private ReferenceService referenceService;


    @Test
    public void listPropertyGoal() {
        List<SelectableDto> res = referenceService.listReference(AvailableReference.PROPERTY_GOAL, null);
        assertEquals(2, res.size());
        assertEquals("RENT", res.get(0).getKey());
    }

    @Test
    public void listPropertyType() {
        List<SelectableDto> res = referenceService.listReference(AvailableReference.PROPERTY_TYPE, "RESIDENTIAL");

        assertEquals(11, res.size());
        assertEquals("APARTMENT", res.get(0).getKey());
    }

    @Test
    public void listPropertyGroup() {
        List<SelectableDto> res = referenceService.listReference(AvailableReference.PROPERTY_GROUP, null);

        assertEquals(4, res.size());
        assertEquals("COMMERCIAL", res.get(0).getKey());
    }
}
