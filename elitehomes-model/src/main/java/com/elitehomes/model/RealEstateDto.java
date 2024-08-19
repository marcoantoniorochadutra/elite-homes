package com.elitehomes.model;

import com.elitehomes.core.entity.base.Versionable;
import com.elitehomes.model.base.ExcludedFieldDto;
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
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateDto implements Versionable, ExcludedFieldDto {

    private Long id;
    private Instant createdAt;
    private Short version;

    private String companyRegNumber;
    private String creci;

    private AddressDto address;
    private ContactDto contact;


}
