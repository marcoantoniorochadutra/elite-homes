package com.elitehomes.domain.ref;


import java.util.Arrays;

import com.elitehomes.core.entity.base.Selectable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PropertyType implements Selectable {

    RESIDENTIAL(PropertyGroup.RESIDENTIAL, 0, "Residential"),
    APARTMENT(PropertyGroup.RESIDENTIAL, 0, "Apartment"),
    HOUSE(PropertyGroup.RESIDENTIAL, 0, "House"),
    CONDOMINIUM(PropertyGroup.RESIDENTIAL, 0, "Condominium"),
    FARM_HOUSE(PropertyGroup.RESIDENTIAL, 0, "Farm House"),
    ROOF(PropertyGroup.RESIDENTIAL, 0, "Rooftop"),
    DUPLEX(PropertyGroup.RESIDENTIAL, 0, "Duplex"),
    STUDIO(PropertyGroup.RESIDENTIAL, 0, "Studio"),
    KITNET(PropertyGroup.RESIDENTIAL, 0, "Kitnet"),
    LAND(PropertyGroup.RESIDENTIAL, 0, "Land"),
    LOFT(PropertyGroup.RESIDENTIAL, 0, "Loft"),

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


    private final PropertyGroup propertyGroup;
    private final Integer ordinal;
    private final String value;


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
