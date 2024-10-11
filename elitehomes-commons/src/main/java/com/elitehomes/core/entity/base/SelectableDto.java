package com.elitehomes.core.entity.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@JsonInclude(Include.NON_NULL)
public class SelectableDto {

    private String key;
    private String value;
    private Boolean active;

    public SelectableDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public SelectableDto(Short key, String value, String additional, Boolean active) {
        buildSelectable(key, value, additional);
        this.active = active;
    }

    public SelectableDto(Short key, String value, String additional) {
        buildSelectable(key, value, additional);
    }

    private void buildSelectable(Short key, String value, String additional) {
        this.key = key.toString();
        if (StringUtils.isBlank(additional)) {
            this.value = value;
        } else {
            this.value = additional + " - " + value;
        }
    }

    public SelectableDto(String key) {
        this.key = key;
    }
}
