package com.elitehomes.domain.ref;


import java.util.Arrays;

import com.elitehomes.core.entity.base.Selectable;

public enum PropertyGoal implements Selectable {

    RENT(0, "Rent"),
    SALE(1, "Sale");

    private final Integer ordinal;
    private final String value;

    PropertyGoal(Integer ordinal, String value) {
        this.ordinal = ordinal;
        this.value = value;
    }

	public static PropertyGoal fromStr(String value) {
		return Arrays.stream(PropertyGoal.values())
				.filter(e -> e.value.equalsIgnoreCase(value) || e.name().equalsIgnoreCase(value)).findAny()
				.orElseThrow(() -> new RuntimeException("INVALID GOAL: " + value));
	}

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
