package com.elitehomes.domain.entity;


import com.elitehomes.domain.base.LifeCycleFields;
import com.elitehomes.domain.base.EntityLifeCycle;
import com.elitehomes.domain.converter.ContactConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RealEstate extends EntityLifeCycle {

	@NotNull
	@Column(length = 50)
	private String companyRegNumber;

	@NotNull
	@Column(length = 50)
	private String creci;

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_address_real_estate"))
    private Address address;

	@Valid
	@NotNull
	@Column(columnDefinition = "json")
	@Convert(converter = ContactConverter.class)
	private Contact contact;

}
