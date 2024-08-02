package com.elitehomes.domain.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private String email;
    private String phone;
    private String cellphone;
}
