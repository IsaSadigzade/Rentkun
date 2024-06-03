package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Jaguar {
    E_Pace("E-Pace"), F_Pace("F-Pace"), F_Pace_R("F-Pace R"), F_Type("F-Type"), F_Type_R("F-Type R"),
    F_Type_S("F-Type S"), I_Pace("I-Pace"), XE("XE"), XF("XF"), XFR("XFR"), XJ("XJ"), XJ_L("XJ L");

    private final String model;

    Jaguar(String model) {
        this.model = model;
    }
}
