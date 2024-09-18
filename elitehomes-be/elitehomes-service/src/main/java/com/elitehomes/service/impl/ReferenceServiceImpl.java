package com.elitehomes.service.impl;

import com.elitehomes.core.auth.LoginDto;
import com.elitehomes.core.config.LocaleContext;
import com.elitehomes.core.entity.base.Internationalizable;
import com.elitehomes.core.entity.base.Selectable;
import com.elitehomes.domain.ref.PropertyGoal;
import com.elitehomes.domain.ref.PropertyGroup;
import com.elitehomes.domain.ref.PropertyType;
import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.service.ReferenceService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Override
    public List<SelectableDto> listPropertyGoal() {
        return getEnumValueAsSelectableDto(PropertyGoal.class);
    }

    @Override
    public List<SelectableDto> listPropertyGroup() {
        return getEnumValueAsSelectableDto(PropertyGroup.class);
    }

    @Override
    public List<SelectableDto> listPropertyType(String group) {
        return Arrays.stream(PropertyType.values())
                .filter(propertyType -> propertyType.getPropertyGroup().name().equalsIgnoreCase(group) || Objects.nonNull(propertyType.getOrdinal()))
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
