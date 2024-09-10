package com.elitehomes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
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
