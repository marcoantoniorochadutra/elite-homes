package com.elitehomes.domain.entity;

import com.elitehomes.core.entity.base.Selectable;

import java.beans.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "uf", name = "uk_state_uf"),
        @UniqueConstraint(columnNames = "ibge", name = "uk_state_ibge")})
public class State implements Selectable {

    @Id
    private Short id;

    @NotNull
    private Short ibge;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    @Column(length = 3)
    private String uf;

    @NotNull
    @Column(columnDefinition = "bit")
    private Boolean active;


    @Override
    @Transient
    public String getKey() {
        return uf;
    }

    @Override
    @Transient
    public String getValue() {
        return uf;
    }
}
