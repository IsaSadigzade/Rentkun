package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Lada {
    _2101("2101"), _21011("21011"), _21013("21013"), _2102("2102"), _2103("2103"), _2104("2104"), _21045("21045"),
    _2105("2105"), _2106("2106"), _2107("2107"), _2108("2108"), _2109("2109"), _21099("21099"), _2110("2110"),
    _21103("21103"), _2111("2111"), _2112("2112"), _2113("2113"), _2114("2114"), _2115("2115"), _2121_Niva("2121 Niva (4x4)"),
    _2131_Niva("2131 Niva (4x4)"), Granta("Granta"), Granta_Cross("Granta Cross"), Kalina("Kalina"), Largus("Largus"),
    Largus_Cross("Largus Cross"), Niva("Niva"), Niva_Bronto("Niva Bronto"), Niva_Travel("Niva Travel"), Oka("Oka"),
    Priora("Priora"), Vesta("Vesta"), Vesta_Cross("Vesta Cross"), Vesta_SW_Cross("Vesta SW Cross"), XRAY("XRAY");

    private final String model;

    Lada(String model) {
        this.model = model;
    }
}
