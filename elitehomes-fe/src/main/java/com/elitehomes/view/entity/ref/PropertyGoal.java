package com.elitehomes.view.entity.ref;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@AllArgsConstructor
public enum PropertyGoal {

    RENT(0, "Rent"),
    SALE(1, "Sale");

    private final Integer ordinal;
    private final String value;

    public static PropertyGoal fromStr(String value) {
        return Arrays.stream(PropertyGoal.values())
                .filter(e -> e.value.equalsIgnoreCase(value) || e.name().equalsIgnoreCase(value)).findAny()
                .orElseThrow(() -> new RuntimeException("INVALID GOAL: " + value));
    }

}
