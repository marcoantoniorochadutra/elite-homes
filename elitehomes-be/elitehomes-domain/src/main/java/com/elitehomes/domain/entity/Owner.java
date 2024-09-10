package com.elitehomes.domain.entity;


import com.elitehomes.core.entity.base.Selectable;
import com.elitehomes.domain.base.EntityLifeCycle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Owner extends EntityLifeCycle implements Selectable {


    private String name;

    @NotNull
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$")
    @Column(length = 100)
    private String email;
    private String phone;
    private String nationalRegistry;
    private String country;
    private String state;
    private String city;

    @Builder(setterPrefix = "with")
    public Owner(Long id, Instant createdAt, Short version, String name, String email, String phone, String nationalRegistry, String country, String state, String city) {
        super(id, createdAt, version);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nationalRegistry = nationalRegistry;
        this.country = country;
        this.state = state;
        this.city = city;
    }

    @Override
    public String getKey() {
        return Objects.toString(getId());
    }

    @Override
    public String getValue() {
        return name;
    }
}
