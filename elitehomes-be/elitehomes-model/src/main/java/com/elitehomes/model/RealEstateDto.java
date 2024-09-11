package com.elitehomes.model;

import com.elitehomes.core.entity.base.Versionable;
import com.elitehomes.core.entity.base.ExcludedFieldDto;
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
public class RealEstateDto implements Versionable, ExcludedFieldDto {

    private Long id;
    private Instant createdAt;
    private Short version;

    private String tenantKey;

    private String name;
    private String companyRegNumber;
    private String creci;

    private AddressDto address;
    private ContactDto contact;


}
