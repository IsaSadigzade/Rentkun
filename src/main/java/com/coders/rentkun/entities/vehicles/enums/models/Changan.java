package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Changan {
    Alsvin("Alsvin"),
    Alsvin_V3("Alsvin V3"),
    Alsvin_V7("Alsvin V7"),
    Benni("Benni"),
    Benni_E_Star("Benni E-Star"),
    CM_8("CM-8"),
    CS_15("CS 15"),
    CS_35("CS 35"),
    CS_35_Plus("CS 35 Plus"),
    CS_55_Plus("CS 55 Plus"),
    CS_75("CS 75"),
    CS_75_Plus("CS 75 Plus"),
    CS_85("CS 85"),
    CS_95("CS 95"),
    CX_20("CX 20"),
    Deepal_S7("Deepal S7"),
    EU_Love("EU-Love"),
    Eado("Eado"),
    Eado_Plus("Eado Plus"),
    Eado_XT("Eado XT"),
    F70_Hunter("F70 (Hunter)"),
    Honor("Honor"),
    Oshan_Z6("Oshan Z6"),
    Oshan_A600("Oshan A600"),
    Qiyuan_A05("Qiyuan A05"),
    Qiyuan_A06("Qiyuan A06"),
    Qiyuan_A07("Qiyuan A07"),
    Qiyuan_Q05("Qiyuan Q05"),
    Raeton("Raeton"),
    Star("Star"),
    Uni_K("Uni-K"),
    Uni_K_iDD("Uni-K iDD"),
    Uni_T("Uni-T"),
    Uni_V("Uni-V");

    private final String model;

    Changan(String model) {
        this.model = model;
    }
}
