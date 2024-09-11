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
public class AccountDto {

    private String accountIdentifier;
    private Double balance;
}