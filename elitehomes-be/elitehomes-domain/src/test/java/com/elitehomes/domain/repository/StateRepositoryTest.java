package com.elitehomes.domain.repository;



import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.test.support.DomainSupport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StateRepositoryTest extends DomainSupport {

	@Test
	public void listStateForSelectable() {
		List<SelectableDto> listStates = stateRepository.listForSelectable();

		assertEquals(27, listStates.size());
		assertEquals("2", listStates.get(0).getKey());
		assertEquals("AC - Acre", listStates.get(0).getValue());
	}

}
