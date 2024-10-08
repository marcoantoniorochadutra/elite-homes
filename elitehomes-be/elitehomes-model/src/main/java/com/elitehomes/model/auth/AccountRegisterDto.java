package com.elitehomes.model.auth;

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
public class AccountRegisterDto {

	private String name;
	private String email;
	private String password;
	private String nationalRegistry;
}
