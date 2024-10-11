package com.elitehomes.service;

import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.ref.AvailableReference;

import java.util.List;

public interface ReferenceService {

    List<SelectableDto> listReference(AvailableReference reference, String additional);

}
