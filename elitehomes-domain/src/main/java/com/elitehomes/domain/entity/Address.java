package com.elitehomes.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "integer unsigned")
    private Long id;

    private String country;
    private String state;
    private String city;

    @Column(length = 150)
    private String neighborhood;
    private String street;

    @Column(length = 10)
    private String number;
    private String zipCode;
    private String complement;
    private String details;

}
