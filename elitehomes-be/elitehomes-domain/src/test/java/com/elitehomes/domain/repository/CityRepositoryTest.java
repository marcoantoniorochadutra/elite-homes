package com.elitehomes.domain.repository;



import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.test.support.DomainSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CityRepositoryTest extends DomainSupport {


	@Test
	public void shoudlListNull() {
		List<SelectableDto> cities = cityRepository.listForSelectable(null);
		assertEquals(0, cities.size());
	}

	@Test
	public void shoudlListForSelectableCitiesSC() {
		changeStateStatus("SC", true);

		List<SelectableDto> cities = cityRepository.listForSelectable(null);
		assertEquals(295, cities.size());
	}

}
