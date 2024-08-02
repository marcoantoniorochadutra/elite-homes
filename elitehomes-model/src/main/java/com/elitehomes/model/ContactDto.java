package com.elitehomes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
	private String email;
	private String phone;
	private String cellphone;
}
