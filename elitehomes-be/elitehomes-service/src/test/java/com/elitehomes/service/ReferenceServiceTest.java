package com.elitehomes.service;

import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.service.impl.ReferenceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReferenceServiceTest {


    private ReferenceService referenceService;

    @BeforeEach
    void setUp() {
        referenceService = new ReferenceServiceImpl();
    }

    @Test
    public void listPropertyGoal() {
        List<SelectableDto> res = referenceService.listPropertyGoal();
        assertEquals(2, res.size());
        assertEquals("RENT", res.get(0).getKey());
    }

    @Test
    public void listPropertyType() {
        List<SelectableDto> res = referenceService.listPropertyType(null);

        assertEquals(25, res.size());
        assertEquals("APARTMENT", res.get(0).getKey());
    }

    @Test
    public void listPropertyGroup() {
        List<SelectableDto> res = referenceService.listPropertyGroup();

        assertEquals(4, res.size());
        assertEquals("COMMERCIAL", res.get(0).getKey());
    }
}
