package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Volvo {
    _240("240"), _440("440"), _460("460"), _480("480"), _850("850"), _940("940"), _960("960"), C40("C40"),
    FH_13("FH 13"), FH_460("FH 460"), FH_500("FH 500"), S60("S60"), S80("S80"), S90("S90"),
    V40("V40"), V90_Cross_Country("V90 Cross Country"), XC40("XC40"), XC60("XC60"), XC70("XC70"), XC90("XC90");

    private final String model;

    Volvo(String model) {
        this.model = model;
    }
}
