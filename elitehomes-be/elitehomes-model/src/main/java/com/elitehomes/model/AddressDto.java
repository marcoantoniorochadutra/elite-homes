package com.elitehomes.model;

import com.elitehomes.core.entity.base.SelectableDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class AddressDto {

    private String country;
    private String state;
    private SelectableDto city;
    private String neighborhood;
    private String street;
    private String number;
    private String zipCode;
    private String complement;
    private String description;
}
