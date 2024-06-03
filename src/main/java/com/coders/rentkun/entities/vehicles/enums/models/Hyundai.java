package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Hyundai {
    Accent("Accent"), Avante("Avante"), Azera("Azera"), County("County"), Coupe("Coupe"), Creta("Creta"),
    Elantra("Elantra"), Elantra_N("Elantra N"), Equus("Equus"), Excel("Excel"), Galloper("Galloper"),
    Genesis("Genesis"), Genesis_Coupe("Genesis Coupe"), Getz("Getz"), Grand_Santa_Fe("Grand Santa Fe"),
    Grandeur("Grandeur"), H_100("H 100"), H_1("H-1"), HD_120("HD-120"), HD_45("HD-45"), HD_65("HD-65"),
    HD_72("HD-72"), HD_78("HD-78"), IONIQ("IONIQ"), IONIQ_5("IONIQ 5"), Kona("Kona"), Lantra("Lantra"),
    Matrix("Matrix"), Maxcruz("Maxcruz"), Mufasa("Mufasa"), Palisade("Palisade"), Santa_Cruz("Santa Cruz"),
    Santa_Fe("Santa Fe"), Solaris("Solaris"), Sonata("Sonata"), Staria("Staria"), Terracan("Terracan"),
    Trajet("Trajet"), Tucson("Tucson"), Veloster("Veloster"), Venue("Venue"), Veracruz("Veracruz"), Verna("Verna"),
    XG("XG"), i10("i10"), i20("i20"), i30("i30"), i40("i40"), ix35("ix35"), ix55("ix55");

    private final String model;

    Hyundai(String model) {
        this.model = model;
    }
}
