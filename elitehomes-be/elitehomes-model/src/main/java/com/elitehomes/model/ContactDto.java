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
public class ContactDto {
	private String email;
	private String phone;
	private String cellphone;
}
