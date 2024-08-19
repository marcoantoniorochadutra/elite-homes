package com.elitehomes.model;

import com.elitehomes.core.entity.base.Versionable;
import com.elitehomes.model.base.ExcludedFieldDto;
import com.elitehomes.model.base.SelectableDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PropertyDto implements Versionable, ExcludedFieldDto {

    private Long id;
    private Instant createdAt;
    private Short version;

    private Integer numBedroom;
    private Integer numBathroom;
    private Integer numSuite;
    private Integer parkingSpaces;

    private SelectableDto owner;
    private SelectableDto goal;
    private SelectableDto type;

    private Double value;

    private String description;
    private String valueDescription;

    private AddressDto address;

}
