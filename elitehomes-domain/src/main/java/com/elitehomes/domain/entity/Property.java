package com.elitehomes.domain.entity;


import com.elitehomes.domain.ref.PropertyGoal;
import com.elitehomes.domain.ref.PropertyType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "integer unsigned")
    private Long id;

    @Column(columnDefinition = "timestamp")
    private Instant createdAt;

    @Size(min = 0, max = 15)
    private Integer numBedroom;

    @Size(min = 0, max = 15)
    private Integer numBathroom;

    @Size(min = 0, max = 15)
    private Integer numSuite;

    @Size(min = 0, max = 15)
    private Integer parkingSpaces;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "goal", columnDefinition = "tinyint unsigned")
    private PropertyGoal goal;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", columnDefinition = "tinyint unsigned")
    private PropertyType type;

    private Double value;

    private String details;
    private String valueDetails;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "address_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_address_property"))
    private Address address;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name="owner_id", foreignKey = @ForeignKey(name = "fk_owner_property"))
    private Owner owner;
}
