package com.elitehomes.model;

import com.elitehomes.core.entity.base.Versionable;
import com.elitehomes.core.entity.base.ExcludedFieldDto;
import com.elitehomes.core.entity.base.SelectableDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PropertyDto implements Versionable, ExcludedFieldDto {

    private Long id;
    private Instant createdAt;
    private Short version;

    private String title;

    private Integer numBedroom;
    private Integer numBathroom;
    private Integer numSuite;
    private Integer parkingSpaces;

    private SelectableDto owner;
    private SelectableDto goal;
    private SelectableDto type;

    private Double price;

    private String description;
    private String valueDescription;



    private AddressDto address;

}
