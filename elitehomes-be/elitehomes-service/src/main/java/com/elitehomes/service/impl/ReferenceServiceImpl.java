package com.elitehomes.service.impl;

import com.elitehomes.core.config.LocaleContext;
import com.elitehomes.core.entity.base.Internationalizable;
import com.elitehomes.core.entity.base.Selectable;
import com.elitehomes.domain.config.TenantContext;
import com.elitehomes.domain.ref.AvailableReference;
import com.elitehomes.domain.ref.PropertyGoal;
import com.elitehomes.domain.ref.PropertyGroup;
import com.elitehomes.domain.ref.PropertyType;
import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.repository.CityRepository;
import com.elitehomes.service.ReferenceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ReferenceServiceImpl implements ReferenceService {

    private final CityRepository cityRepository;

    @Autowired
    public ReferenceServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public List<SelectableDto> listReference(AvailableReference reference, String additional) {
        System.err.println("Actual DB: " + TenantContext.getCurrentTenant());
        switch (reference) {
            case PROPERTY_GROUP: return getEnumValueAsSelectableDto(PropertyGroup.class);
            case PROPERTY_GOAL: return getEnumValueAsSelectableDto(PropertyGoal.class);
            case PROPERTY_TYPE: return getPropertyType(additional);
            case STATE: return getState(additional);
            case CITIES: return getCities(additional);
        }

        return null;
    }

    private List<SelectableDto> getCities(String additional) {
        System.err.println("Actual DB: " + TenantContext.getCurrentTenant());

        return cityRepository.listForSelectable(additional);
    }

    private List<SelectableDto> getState(String additional) {
        return null;
    }

    private List<SelectableDto> getPropertyType(String additional) {
        return Arrays.stream(PropertyType.values())
                .filter(propertyType -> propertyType.getPropertyGroup().name().equalsIgnoreCase(additional))
                .map(item -> new SelectableDto(item.getKey(), item.getTranslation(LocaleContext.getCurrentLocale().getLanguage())))
                .sorted(Comparator.comparing(SelectableDto::getValue))
                .collect(Collectors.toList());
    }


    public <E extends Enum<E> & Selectable & Internationalizable> List<SelectableDto> getEnumValueAsSelectableDto(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(item -> new SelectableDto(item.getKey(), item.getTranslation(LocaleContext.getCurrentLocale().getLanguage())))
                .sorted(Comparator.comparing(SelectableDto::getValue))
                .collect(Collectors.toList());
    }
}
