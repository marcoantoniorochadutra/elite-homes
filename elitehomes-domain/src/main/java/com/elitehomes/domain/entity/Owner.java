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

import java.util.Objects;


@Data
@Setter
@Getter
@Builder(setterPrefix = "with")
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

    @Override
    public String getKey() {
        return Objects.toString(getId());
    }

    @Override
    public String getValue() {
        return name;
    }
}
