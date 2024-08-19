package com.elitehomes.service.converter;

import com.elitehomes.domain.entity.Owner;
import com.elitehomes.domain.entity.Property;
import com.elitehomes.domain.repository.OwnerRepository;
import com.elitehomes.domain.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaRepositorySelector extends dozerSelectableConverter{

    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public JpaRepositorySelector(PropertyRepository propertyRepository,
                                 OwnerRepository ownerRepository) {
        this.propertyRepository = propertyRepository;
        this.ownerRepository = ownerRepository;
    }


    @Override
    protected JpaRepository<?, Long> getRepository(Class<?> clazz) {

        if(Property.class.isAssignableFrom(clazz)) {
            return propertyRepository;
        }
        if(Owner.class.isAssignableFrom(clazz)) {
            return ownerRepository;
        }

        return null;
    }
}
