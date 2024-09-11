package com.elitehomes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Boolean active;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
        this.active = true;
    }
}
