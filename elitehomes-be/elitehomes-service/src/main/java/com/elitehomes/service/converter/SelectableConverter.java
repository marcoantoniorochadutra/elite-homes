package com.elitehomes.service.converter;

import com.elitehomes.core.entity.base.Selectable;
import com.elitehomes.core.entity.base.SelectableDto;
import com.github.dozermapper.core.CustomConverter;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;

@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class SelectableConverter implements CustomConverter {

    private static final Logger log = LoggerFactory.getLogger(SelectableConverter.class);

    protected abstract JpaRepository<?, Long> getRepository(Class<?> destinationClass);

    @Override

    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue,
                          Class<?> destinationClass,
                          Class<?> sourceClass) {

        log.error("Source Class {}\n Destination Class {}", sourceClass, destinationClass);

        if (Objects.isNull(sourceFieldValue)) {
            return null;
        }

        if (isSelectable(sourceClass)) {
            SelectableDto source = (SelectableDto) sourceFieldValue;

            if (isDestinationEnum(destinationClass)) {
                return getEnum((Class<? extends Enum>) destinationClass, source);
            } else if(isSelectableImpl(destinationClass)) {
                JpaRepository<?, Long> repository = getRepository(destinationClass);
                return repository.getReferenceById(NumberUtils.toLong(source.getKey()));
            } else {
                return null;
            }
        }

        Selectable selectable = (Selectable) sourceFieldValue;
        return new SelectableDto(selectable.getKey(), selectable.getValue());
    }

    private Enum getEnum(Class<? extends Enum> destinationClass, SelectableDto source) {
        try {
            return Enum.valueOf(destinationClass, source.getKey());
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isDestinationEnum(Class<?> destinationClass) {
        return destinationClass.isEnum();
    }

    private boolean isSelectableImpl(Class<?> sourceClass) {
        return Selectable.class.isAssignableFrom(sourceClass);
    }

    private boolean isSelectable(Class<?> sourceClass) {
        return SelectableDto.class.isAssignableFrom(sourceClass);
    }

}
