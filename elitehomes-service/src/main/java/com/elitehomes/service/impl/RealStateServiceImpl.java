package com.elitehomes.service.impl;

import com.elitehomes.domain.entity.RealEstate;
import com.elitehomes.domain.repository.RealEstateRepository;
import com.elitehomes.model.RealEstateDto;
import com.elitehomes.service.RealEstateService;
import com.elitehomes.service.base.AbstractCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class RealStateServiceImpl extends AbstractCrudService<RealEstate, RealEstateDto> implements RealEstateService {

    private final ModelMapper modelMapper;
    private final RealEstateRepository realEstateRepository;

    @Autowired
    protected RealStateServiceImpl(ModelMapper modelMapper,
                                   RealEstateRepository realEstateRepository) {
        super(modelMapper);
        this.modelMapper = modelMapper;
        this.realEstateRepository = realEstateRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected RealEstateRepository getRepository() {
        return realEstateRepository;
    }

    @Override
    protected Class<RealEstate> getDomainClass() {
        return RealEstate.class;
    }

    @Override
    protected Class<RealEstateDto> getModelClass() {
        return RealEstateDto.class;
    }

}
