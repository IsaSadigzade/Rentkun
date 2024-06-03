package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Opel {
    Antara("Antara"), Ascona("Ascona"), Astra("Astra"), Astra_OPC("Astra OPC"),
    Calibra("Calibra"), Cascada("Cascada"), Combo("Combo"), Corsa("Corsa"), Crossland_X("Crossland X"),
    Frontera("Frontera"),
    Grandland_X("Grandland X"),
    Insignia("Insignia"), Insignia_OPC("Insignia OPC"),
    Meriva("Meriva"), Mokka("Mokka"), Mokka_X("Mokka X"), Monterey("Monterey"), Movano("Movano"),
    Omega("Omega"),
    Rekord("Rekord"),
    Senator("Senator"), Signum("Signum"), Sintra("Sintra"),
    Tigra("Tigra"),
    Vectra("Vectra"), Vita("Vita"), Vivaro("Vivaro"),
    Zafira("Zafira");

    private final String model;

    Opel(String model) {
        this.model = model;
    }
}
