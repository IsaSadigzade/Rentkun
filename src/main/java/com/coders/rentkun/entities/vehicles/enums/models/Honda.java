package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Honda {
    Accord("Accord"), CR_V("CR-V"), City("City"), Civic("Civic"), Element("Element"), Fit("Fit"), Grace("Grace"),
    HR_V("HR-V"), Insight("Insight"), M_NV("M-NV"), Odyssey("Odyssey"), Pilot("Pilot");

    private final String model;

    Honda(String model) {
        this.model = model;
    }
}
