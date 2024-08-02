package com.elitehomes.service;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.model.PropertyDto;

public interface PropertyService {

    PropertyDto create(PropertyDto propertyDto, LoginDto login);

}
