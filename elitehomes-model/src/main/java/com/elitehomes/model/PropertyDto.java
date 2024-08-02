package com.elitehomes.model;

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
@Builder
public class PropertyDto {

    private Long id;
    private Instant createdAt;

    private Integer numBedroom;
    private Integer numBathroom;
    private Integer numSuite;
    private Integer parkingSpaces;

    private SelectableDto goal;
    private SelectableDto type;

    private Double value;
    private String details;
    private String valueDetails;
}
