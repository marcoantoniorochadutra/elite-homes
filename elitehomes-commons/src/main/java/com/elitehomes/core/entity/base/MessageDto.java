package com.elitehomes.core.entity.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class MessageDto {

    private String message;
}
