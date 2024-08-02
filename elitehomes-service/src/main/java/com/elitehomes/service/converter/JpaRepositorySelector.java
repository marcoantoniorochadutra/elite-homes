package com.elitehomes.service.converter;

import com.elitehomes.domain.entity.Property;
import com.elitehomes.domain.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaRepositorySelector {

    private final PropertyRepository propertyRepository;

    @Autowired
    public JpaRepositorySelector(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public JpaRepository<?, Long> getRepo(Class<?> clazz) {
        if(Property.class.isAssignableFrom(clazz)) {
            return propertyRepository;
        }

        return null;
    }

}
