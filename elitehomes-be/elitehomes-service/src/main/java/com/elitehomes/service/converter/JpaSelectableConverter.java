package com.elitehomes.service.converter;

import com.elitehomes.domain.entity.City;
import com.elitehomes.domain.entity.Owner;
import com.elitehomes.domain.entity.Property;
import com.elitehomes.domain.repository.CityRepository;
import com.elitehomes.domain.repository.OwnerRepository;
import com.elitehomes.domain.repository.PropertyRepository;
import com.elitehomes.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaSelectableConverter extends SelectableConverter {

    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Autowired
    public JpaSelectableConverter(PropertyRepository propertyRepository,
                                  OwnerRepository ownerRepository,
                                  CityRepository cityRepository,
                                  StateRepository stateRepository) {
        this.propertyRepository = propertyRepository;
        this.ownerRepository = ownerRepository;
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }


    @Override
    protected JpaRepository<?, Long> getRepository(Class<?> clazz) {

        if(Property.class.isAssignableFrom(clazz)) {
            return propertyRepository;
        }

        if (Owner.class.isAssignableFrom(clazz)) {
            return ownerRepository;
        }

        return null;
    }
}
