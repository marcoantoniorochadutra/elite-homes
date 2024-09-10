package com.elitehomes.domain.ref;


import java.util.Arrays;

import com.elitehomes.core.entity.base.Selectable;

public enum PropertyGroup implements Selectable {

	RESIDENTIAL(0, "Residential"),
    INDUSTRIAL(1, "Industrial"),
    COMMERCIAL(2, "Commercial"),
    RURAL(3, "Rural");

    private final Integer ordinal;
    private final String value;

    PropertyGroup(Integer ordinal, String value) {
        this.ordinal = ordinal;
        this.value = value;
    }

	public static PropertyGroup fromStr(String value) {
		return Arrays.stream(PropertyGroup.values())
				.filter(e -> e.value.equalsIgnoreCase(value) || e.name().equalsIgnoreCase(value)).findAny()
				.orElseThrow(() -> new RuntimeException("INVALID PROPERTY GROUP: " + value));
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
