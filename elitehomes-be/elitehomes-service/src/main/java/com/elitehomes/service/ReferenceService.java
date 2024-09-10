package com.elitehomes.service;

import com.elitehomes.model.base.SelectableDto;

import java.util.List;

public interface ReferenceService {

    List<SelectableDto> listPropertyGoal();

    List<SelectableDto> listPropertyGroup();

    List<SelectableDto> listPropertyType();


}
