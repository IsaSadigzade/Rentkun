package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Porsche {
    _718_Boxster("718 Boxster"), Cayenne("Cayenne"), Cayenne_Coupe("Cayenne Coupe"), Cayenne_E_Hybrid("Cayenne E-Hybrid"),
    Cayenne_E_hybrid_Coupe("Cayenne E-hybrid Coupe"), Cayenne_GTS("Cayenne GTS"), Cayenne_S("Cayenne S"),
    Cayenne_S_Coupe("Cayenne S Coupe"), Cayenne_Turbo("Cayenne Turbo"), Cayenne_Turbo_S("Cayenne Turbo S"),
    Cayman("Cayman"), Macan("Macan"), Macan_GTS("Macan GTS"), Macan_S("Macan S"), Macan_T("Macan T"),
    Macan_Turbo("Macan Turbo"), Panamera("Panamera"), Panamera_4("Panamera 4"), Panamera_4_E_Hybrid("Panamera 4 E-Hybrid"),
    Panamera_4S("Panamera 4S"), Panamera_4S_Executive("Panamera 4S Executive"), Panamera_GTS("Panamera GTS"),
    Panamera_S("Panamera S"), Panamera_S_Hybrid("Panamera S Hybrid"), Panamera_Turbo("Panamera Turbo"),
    Panamera_Turbo_Executive("Panamera Turbo Executive"), Panamera_Turbo_S("Panamera Turbo S"), Taycan("Taycan"),
    Taycan_4_Cross_Turismo("Taycan 4 Cross Turismo"), Taycan_4S("Taycan 4S"), Taycan_GTS("Taycan GTS"),
    Taycan_Turbo_S("Taycan Turbo S");

    private final String model;

    Porsche(String model) {
        this.model = model;
    }
}
