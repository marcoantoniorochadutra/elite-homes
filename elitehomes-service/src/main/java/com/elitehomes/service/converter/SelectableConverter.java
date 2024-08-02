package com.elitehomes.service.converter;

import com.elitehomes.core.entity.base.Selectable;
import com.elitehomes.model.base.SelectableDto;
import org.apache.commons.lang3.math.NumberUtils;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class SelectableConverter<S, D> implements Converter<S, D> {


    private final JpaRepositorySelector jpaRepositorySelector;

    public SelectableConverter(JpaRepositorySelector jpaRepositorySelector) {
        this.jpaRepositorySelector = jpaRepositorySelector;
    }

    @Override
    public D convert(MappingContext<S, D> context) {
        if (Objects.nonNull(context.getSource())) {

            if (context.getSourceType().isAssignableFrom(SelectableDto.class)) {
                SelectableDto selectable = (SelectableDto) context.getSource();
                if (context.getDestinationType().isEnum()) {
                    return context.getDestinationType().cast(Enum.valueOf((Class<Enum>) context.getDestinationType(), selectable.getKey()));

                }
                Long id = NumberUtils.toLong(selectable.getKey());
                return context.getDestinationType().cast(jpaRepositorySelector.getRepo(context.getDestinationType()).findById(id).orElse(null));
            }

            if (context.getDestinationType().isAssignableFrom(SelectableDto.class)) {
                Selectable selectable = (Selectable) context.getSource();
                SelectableDto selectableDto = new SelectableDto(selectable.getKey(), selectable.getValue());
                return context.getDestinationType().cast(selectableDto);
            }
        }

        return null;
    }
}
