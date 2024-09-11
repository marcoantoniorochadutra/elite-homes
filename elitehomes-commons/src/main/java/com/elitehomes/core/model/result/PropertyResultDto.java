package com.elitehomes.core.model.result;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResultDto {

    private Long id;
    private Instant createdAt;
    private Short version;

    private String title;
    private String description;

    private Integer numBedroom;
    private Integer numBathroom;
    private Integer numSuite;
    private Integer parkingSpaces;

    private Double value;

}
