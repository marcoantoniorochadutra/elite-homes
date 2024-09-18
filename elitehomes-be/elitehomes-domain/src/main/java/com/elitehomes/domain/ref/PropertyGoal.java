package com.elitehomes.domain.ref;


import java.util.Arrays;

import com.elitehomes.core.entity.base.Internationalizable;
import com.elitehomes.core.entity.base.Selectable;

public enum PropertyGoal implements Selectable, Internationalizable {

    RENT(0, "Rent", "Aluguel"),
    SALE(1, "Sale", "Venda");

    private final Integer ordinal;
    private final String valueEn;
    private final String valuePtBr;

    PropertyGoal(Integer ordinal, String value, String valuePtBr) {
        this.ordinal = ordinal;
        this.valueEn = value;
        this.valuePtBr = valuePtBr;
    }

    public static PropertyGoal fromStr(String value) {
        return Arrays.stream(PropertyGoal.values())
                .filter(e -> e.valueEn.equalsIgnoreCase(value) || e.name().equalsIgnoreCase(value)).findAny()
                .orElseThrow(() -> new RuntimeException("INVALID GOAL: " + value));
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
        if (locale.contains("pt")) {
            return valuePtBr;
        } else if (locale.contains("en")) {
            return valueEn;
        }
        return valueEn;
    }
}
