package com.elitehomes.service.converter;

import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.entity.Address;
import com.elitehomes.domain.entity.City;
import com.elitehomes.domain.repository.CityRepository;
import com.elitehomes.model.AddressDto;
import com.github.dozermapper.core.DozerConverter;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AddressConverter extends DozerConverter<Address, AddressDto>{

	private final CityRepository cityRepository;

	@Autowired
	public AddressConverter(CityRepository cityRepository) {
        super(Address.class, AddressDto.class);
        this.cityRepository = cityRepository;
	}


	@Override
	public AddressDto convertTo(Address source, AddressDto destination) {
		if(Objects.isNull(destination)) {
			destination = new AddressDto();
		}

		destination.setCountry("Brasil");
		destination.setCity(new SelectableDto(source.getCity().getKey(), source.getCity().getValue()));
		destination.setState(source.getCity().getState().getValue());
		destination.setComplement(getNullable(source.getComplement()));
		destination.setNeighborhood(getNullable(source.getNeighborhood()));
		destination.setNumber(getNullable(source.getNumber()));
		destination.setStreet(getNullable(source.getStreet()));
		destination.setZipCode(getNullable(source.getZipCode()));
		destination.setDescription(getNullable(source.getDescription()));
		return destination;
	}

	@Override
	public Address convertFrom(AddressDto source, Address destination) {
		if(Objects.isNull(destination)) {
			destination = new Address();
		}

		City city = cityRepository.getReferenceById(NumberUtils.toLong(source.getCity().getKey()));
		destination.setCity(city);
		destination.setCountry("Brasil");
		destination.setComplement(getNullable(source.getComplement()));
		destination.setNeighborhood(getNullable(source.getNeighborhood()));
		destination.setNumber(getNullable(source.getNumber()));
		destination.setStreet(getNullable(source.getStreet()));
		destination.setZipCode(getNullable(source.getZipCode()));
		destination.setDescription(getNullable(source.getDescription()));

		return destination;
	}

	private String getNullable(String value) {
		if (StringUtils.isNotEmpty(value)) {
			return value;
		}
		return null;
	}
}
