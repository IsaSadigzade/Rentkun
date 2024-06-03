package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Cadillac {
    ATS("ATS"),
    CT4("CT4"),
    CT5("CT5"),
    CT6("CT6"),
    CTS("CTS"),
    DTS("DTS"),
    ELR("ELR"),
    Escalade("Escalade"),
    SRX("SRX"),
    XT4("XT4"),
    XTS("XTS");

    private final String model;

    Cadillac(String model) {
        this.model = model;
    }
}
