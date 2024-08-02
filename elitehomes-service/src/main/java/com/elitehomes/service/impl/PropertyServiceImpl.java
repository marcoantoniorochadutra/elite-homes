package com.elitehomes.service.impl;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.domain.entity.Property;
import com.elitehomes.model.PropertyDto;
import com.elitehomes.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PropertyServiceImpl implements PropertyService {

    private ModelMapper modelMapper;

    @Autowired
    public PropertyServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PropertyDto create(PropertyDto propertyDto, LoginDto login) {
        Property property = modelMapper.map(propertyDto, Property.class);
        System.err.println(property);

        return null;
    }
}
//Property(id=null, createdAt=null,
// numBedroom=2, numBathroom=2, numSuite=1, parkingSpaces=2,
// goal=null, type=null, value=2500.0,
// details=description, valueDetails=value description, address=null, owner=null)
