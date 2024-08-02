package com.elitehomes.service.converter;


import com.elitehomes.domain.entity.Property;
import com.elitehomes.model.PropertyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

    @Autowired
    private JpaRepositorySelector jpaRepositorySelector;


    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        setModelToDomain(mapper);
        setDomainToModel(mapper);
        setClientToSomething(mapper);
        return mapper;
    }

	private void setClientToSomething(ModelMapper mapper) {

	}

	private void setDomainToModel(ModelMapper mapper) {
	}

	private void setModelToDomain(ModelMapper mapper) {
        convertPropertyToDomain(mapper);

	}

    private void convertPropertyToDomain(ModelMapper mapper) {
        SelectableConverter<PropertyDto, Property> converter = new SelectableConverter<>(jpaRepositorySelector);

         mapper.typeMap(PropertyDto.class, Property.class, "convert")
                .addMappings(m -> m.using(converter).map(PropertyDto::getType, Property::setType))
                .addMappings(m -> m.using(converter).map(PropertyDto::getGoal, Property::setGoal)).getName();
    }


}
