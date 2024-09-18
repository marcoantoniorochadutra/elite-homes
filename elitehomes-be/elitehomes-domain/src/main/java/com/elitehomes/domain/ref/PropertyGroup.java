package com.elitehomes.domain.ref;


import java.util.Arrays;

import com.elitehomes.core.entity.base.Internationalizable;
import com.elitehomes.core.entity.base.Selectable;

public enum PropertyGroup implements Selectable, Internationalizable {

    RESIDENTIAL(0, "Residential", "Residencial"),
    INDUSTRIAL(1, "Industrial", "Industrial"),
    COMMERCIAL(2, "Commercial", "Comercial"),
    RURAL(3, "Rural", "Rural");

    private final Integer ordinal;
    private final String valueEn;
    private final String valuePtBr;

    PropertyGroup(Integer ordinal, String valueEn, String valuePtBr) {
        this.ordinal = ordinal;
        this.valuePtBr = valuePtBr;
        this.valueEn = valueEn;
    }

    public static PropertyGroup fromStr(String value) {
        return Arrays.stream(PropertyGroup.values())
                .filter(e -> e.valueEn.equalsIgnoreCase(value) || e.name().equalsIgnoreCase(value)).findAny()
                .orElseThrow(() -> new RuntimeException("INVALID PROPERTY GROUP: " + value));
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return valueEn;
    }

    @Override
    public String getTranslation(String locale) {
        if (locale.equalsIgnoreCase("pt-BR")) {
            return valuePtBr;
        } else if (locale.equalsIgnoreCase("en")) {
            return valueEn;
        }
        return valueEn;
    }
}
