package com.elitehomes.domain.ref;


import java.util.Arrays;

import com.elitehomes.core.entity.base.Internationalizable;
import com.elitehomes.core.entity.base.Selectable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public
enum PropertyType implements Selectable, Internationalizable {

    RESIDENTIAL(PropertyGroup.RESIDENTIAL, 0, "Residential", "Residência"),
    APARTMENT(PropertyGroup.RESIDENTIAL, 0, "Apartment", "Apartamento"),
    HOUSE(PropertyGroup.RESIDENTIAL, 0, "House", "Casa"),
    CONDOMINIUM(PropertyGroup.RESIDENTIAL, 0, "Condominium", "Condomínio"),
    FARM_HOUSE(PropertyGroup.RESIDENTIAL, 0, "Farm House", "Chácara"),
    PENT_HOUSE(PropertyGroup.RESIDENTIAL, 0, "Penthouse", "Cobertura"),
    DUPLEX(PropertyGroup.RESIDENTIAL, 0, "Duplex", "Duplex"),
    STUDIO(PropertyGroup.RESIDENTIAL, 0, "Studio", "Studio"),
    KITNET(PropertyGroup.RESIDENTIAL, 0, "Kitnet", "Kitnet"),
    LAND(PropertyGroup.RESIDENTIAL, 0, "Land", "Lote"),
    LOFT(PropertyGroup.RESIDENTIAL, 0, "Loft", "Loft"),

    STORAGE(PropertyGroup.INDUSTRIAL, 1, "Storage", "Armázem"),
    SHED(PropertyGroup.INDUSTRIAL, 1, "Shed", "Galpão"),
    GARAGE(PropertyGroup.INDUSTRIAL, 1, "Garage", "Garagem"),

    COMMERCIAL(PropertyGroup.COMMERCIAL, 2, "Commercial", "Comercial"),
    OFFICE(PropertyGroup.COMMERCIAL, 2, "Office", "Escritório"),
    HOTEL(PropertyGroup.COMMERCIAL, 2, "Hotel", "Hotel"),
    STORE(PropertyGroup.COMMERCIAL, 2, "Store", "Loja"),
    INN(PropertyGroup.COMMERCIAL, 2, "Inn", "Pousada"),
    BUILDING(PropertyGroup.COMMERCIAL, 2, "Building", "Prédio"),
    COMMERCIAL_ROOM(PropertyGroup.COMMERCIAL, 2, "Commercial Room", "Sala Comercial"),
    COMMERCIAL_LAND(PropertyGroup.COMMERCIAL, 2, "Commercial Land", "Terreno"),

    FARM(PropertyGroup.RURAL, 3, "Farm", "Fazenda"),
    RANCH(PropertyGroup.RURAL, 3, "Ranch", "Rancho"),
    FARMSTEAD(PropertyGroup.RURAL, 3, "Farmstead", "Sítio");


    private final PropertyGroup propertyGroup;
    private final Integer ordinal;
    private final String valueEn;
    private final String valuePtBr;

    public static PropertyType fromStr(String value) {
        return Arrays.stream(PropertyType.values())
                .filter(e -> e.valueEn.equalsIgnoreCase(value)
                        || e.valuePtBr.equalsIgnoreCase(value)
                        || e.name().equalsIgnoreCase(value)).findAny()
                .orElseThrow(() -> new RuntimeException("INVALID GOAL: " + value));
    }

    @Override
    public String getTranslation(String locale) {
        if (locale.equalsIgnoreCase("pt-BR")) {
            return valuePtBr;
        } else if (locale.equalsIgnoreCase("en")) {
            return valueEn;
        }
        return valueEn;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return valueEn;
    }

}
