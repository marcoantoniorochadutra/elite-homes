package com.elitehomes.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "integer unsigned")
    private Long id;

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

}
