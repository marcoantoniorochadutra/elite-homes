package com.elitehomes.domain.ref;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AvailableReference {

    PROPERTY_GROUP(false),
    PROPERTY_GOAL(false),
    PROPERTY_TYPE(true),
    STATE(false),
    CITIES(false);

    private final Boolean hasAdditional;

}
