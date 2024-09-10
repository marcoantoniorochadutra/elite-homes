package com.elitehomes.service;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.model.PropertyDto;
import com.elitehomes.model.result.PropertyResultDto;
import com.elitehomes.service.base.CrudService;
import com.elitehomes.service.base.ListService;

public interface PropertyService extends CrudService<PropertyDto>, ListService<PropertyResultDto> {


}
