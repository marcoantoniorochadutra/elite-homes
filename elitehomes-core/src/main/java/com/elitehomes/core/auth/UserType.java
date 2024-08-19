package com.elitehomes.core.auth;

import java.util.Arrays;

import com.elitehomes.core.entity.base.Selectable;

public enum UserType implements Selectable {

	MASTER(0, "Rent"),
	ADMIN(1, "Admin"),
	REALTOR(2, "Realtor"),
	USER(3, "User");

	private final Integer ordinal;
	private final String value;

	UserType(Integer ordinal, String value) {
		this.ordinal = ordinal;
		this.value = value;
	}

	public static UserType fromStr(String value) {
		return Arrays.stream(UserType.values())
				.filter(e -> e.value.equalsIgnoreCase(value) || e.name().equalsIgnoreCase(value))
				.findAny()
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
