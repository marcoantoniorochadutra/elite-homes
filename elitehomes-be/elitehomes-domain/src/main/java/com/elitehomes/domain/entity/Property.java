package com.elitehomes.domain.entity;


import com.elitehomes.domain.base.EntityLifeCycle;
import com.elitehomes.domain.ref.PropertyGoal;
import com.elitehomes.domain.ref.PropertyType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "property")
public class Property extends EntityLifeCycle {

    @NotNull
    @Column(length = 50)
    private String title;

    @Max(15)
    private Integer numBedroom;

    @Max(15)
    private Integer numBathroom;

    @Max(15)
    private Integer numSuite;

    @Max(15)
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

    private String description;
    private String valueDescription;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_attachment_property"))
    @Valid
    private List<Attachment> attachment;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_address_property"))
    private Address address;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id", foreignKey = @ForeignKey(name = "fk_owner_property"))
    private Owner owner;

    @Builder(setterPrefix = "with")
    public Property(Long id, Instant createdAt, Short version, String title, Integer numBedroom, Integer numBathroom, Integer numSuite,
                    Integer parkingSpaces, PropertyGoal goal, PropertyType type, Double value, String description, String valueDescription,
                    List<Attachment> attachment, Address address, Owner owner) {
        super(id, createdAt, version);
        this.title = title;
        this.numBedroom = numBedroom;
        this.numBathroom = numBathroom;
        this.numSuite = numSuite;
        this.parkingSpaces = parkingSpaces;
        this.goal = goal;
        this.type = type;
        this.value = value;
        this.description = description;
        this.valueDescription = valueDescription;
        this.attachment = attachment;
        this.address = address;
        this.owner = owner;
    }
}
