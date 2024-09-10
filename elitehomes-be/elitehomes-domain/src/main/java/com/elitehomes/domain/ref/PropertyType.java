package com.elitehomes.domain.ref;


import java.util.Arrays;

import com.elitehomes.core.entity.base.Selectable;

public enum PropertyType implements Selectable {

    RESIDENTIAL(PropertyGroup.RESIDENTIAL, 0, "Residential"),
    APARTMENT(PropertyGroup.RESIDENTIAL, 0, "Apartment"),
    HOUSE(PropertyGroup.RESIDENTIAL, 0, ""),
    CONDOMINIUM(PropertyGroup.RESIDENTIAL, 0, ""),
    FARM_HOUSE(PropertyGroup.RESIDENTIAL, 0, ""),
    ROOF(PropertyGroup.RESIDENTIAL, 0, ""),
    DUPLEX(PropertyGroup.RESIDENTIAL, 0, ""),
    STUDIO(PropertyGroup.RESIDENTIAL, 0, ""),
    KITNET(PropertyGroup.RESIDENTIAL, 0, ""),
    LAND(PropertyGroup.RESIDENTIAL, 0, ""),
    LOFT(PropertyGroup.RESIDENTIAL, 0, ""),

    STORAGE(PropertyGroup.INDUSTRIAL, 1, "Storage"),
    SHED(PropertyGroup.INDUSTRIAL, 1, "Shed"),
    GARAGE(PropertyGroup.INDUSTRIAL, 1, "Garage"),

    COMMERCIAL(PropertyGroup.COMMERCIAL, 2, "Commercial"),
    DESK(PropertyGroup.COMMERCIAL, 2, "Desk"),
    HOTEL(PropertyGroup.COMMERCIAL, 2, "Hotel"),
    STORE(PropertyGroup.COMMERCIAL, 2, "Store"),
    INN(PropertyGroup.COMMERCIAL, 2, "Inn"),
    BUILDING(PropertyGroup.COMMERCIAL, 2, "Building"),
    COMMERCIAL_ROOM(PropertyGroup.COMMERCIAL, 2, "Commercial Room"),
    COMMERCIAL_LAND(PropertyGroup.COMMERCIAL, 2, "Commercial Land"),

    FARM(PropertyGroup.RURAL, 3, "Farm"),
    RANCH(PropertyGroup.RURAL, 3, "Ranch"),
    FARMSTEAD(PropertyGroup.RURAL, 3, "Farmstead");


    private final Integer ordinal;
    private final PropertyGroup propertyGroup;
    private final String value;

    PropertyType(PropertyGroup propertyGroup, Integer ordinal, String value) {
        this.ordinal = ordinal;
        this.propertyGroup = propertyGroup;
        this.value = value;
    }

	public static PropertyType fromStr(String value) {
		return Arrays.stream(PropertyType.values())
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
