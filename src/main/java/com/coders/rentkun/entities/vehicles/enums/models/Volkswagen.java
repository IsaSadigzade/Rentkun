package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Volkswagen {
    Amarok("Amarok"), Arteon("Arteon"), Atlas("Atlas"), Beetle("Beetle"), Bora("Bora"), Caddy("Caddy"),
    Caravelle("Caravelle"), Crafter("Crafter"), Golf("Golf"), Golf_GTI("Golf GTI"), Golf_Plus("Golf Plus"),
    ID3("ID.3"), ID4("ID.4"), ID4_Crozz("ID.4 Crozz"), ID4_X("ID.4 X"), ID6("ID.6"), ID6_Crozz("ID.6 Crozz"),
    ID7("ID.7"), Jetta("Jetta"), Multivan("Multivan"), Passat("Passat"), Passat_CC("Passat CC"), Phaeton("Phaeton"),
    Pointer("Pointer"), Polo("Polo"), Polo_R_WRC("Polo R WRC"), Scirocco("Scirocco"), Sharan("Sharan"), Taos("Taos"),
    Tayron("Tayron"), Tiguan("Tiguan"), Touareg("Touareg"), Touran("Touran"), Transporter("Transporter"), Vento("Vento");

    private final String model;

    Volkswagen(String model) {
        this.model = model;
    }
}
