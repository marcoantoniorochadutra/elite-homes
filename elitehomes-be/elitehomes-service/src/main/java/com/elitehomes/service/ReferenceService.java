package com.elitehomes.service;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.entity.base.SelectableDto;

import java.util.List;

public interface ReferenceService {

    List<SelectableDto> listPropertyGoal();

    List<SelectableDto> listPropertyGroup();

    List<SelectableDto> listPropertyType(String group);


}
