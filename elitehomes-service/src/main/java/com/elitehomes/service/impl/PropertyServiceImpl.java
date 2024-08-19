package com.elitehomes.service.impl;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.domain.entity.Property;
import com.elitehomes.domain.entity.RealEstate;
import com.elitehomes.domain.repository.AttachmentRepository;
import com.elitehomes.domain.repository.PropertyRepository;
import com.elitehomes.model.PropertyDto;
import com.elitehomes.model.RealEstateDto;
import com.elitehomes.service.PropertyService;
import com.elitehomes.service.base.AbstractCrudService;
import com.elitehomes.service.base.AttachmentService;
import com.github.dozermapper.core.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PropertyServiceImpl extends AbstractCrudService<Property, PropertyDto> implements PropertyService, AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final PropertyRepository propertyRepository;
    private final Mapper modelMapper;


    @Autowired
    public PropertyServiceImpl(AttachmentRepository attachmentRepository,
                               PropertyRepository propertyRepository,
                               Mapper modelMapper) {
        super(modelMapper);
        this.attachmentRepository = attachmentRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected PropertyRepository getRepository() {
        return propertyRepository;
    }

    @Override
    protected Class<Property> getDomainClass() {
        return Property.class;
    }

    @Override
    protected Class<PropertyDto> getModelClass() {
        return PropertyDto.class;
    }

    @Override
    public AttachmentRepository getAttachmentRepository() {
        return attachmentRepository;
    }

}

