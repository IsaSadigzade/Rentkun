package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Toyota {
    Alphard("Alphard"), Aqua("Aqua"), Auris("Auris"), Avalon("Avalon"), Avensis("Avensis"), C_HR("C-HR"),
    Camry("Camry"), Carina("Carina"), Corolla("Corolla"), Corolla_Axio("Corolla Axio"), Corolla_Cross("Corolla Cross"),
    Corolla_Fielder("Corolla Fielder"), Corona("Corona"), Crown("Crown"), Esquire("Esquire"), Estima("Estima"),
    FJ_Cruiser("FJ Cruiser"), Fortuner("Fortuner"), Frontlander("Frontlander"), Funcargo("Funcargo"), Harrier("Harrier"),
    HiAce("HiAce"), Highlander("Highlander"), Hilux("Hilux"), Hilux_Surf("Hilux Surf"), IQ("IQ"), Land_Cruiser("Land Cruiser"),
    Land_Cruiser_Prado("Land Cruiser Prado"), Noah("Noah"), Picnic("Picnic"), Previa("Previa"), Prius("Prius"),
    Prius_C("Prius C"), Prius_PHV("Prius PHV"), Prius_Plus("Prius Plus"), Prius_Prime("Prius Prime"), Prius_V("Prius V"),
    Probox("Probox"), RAV4("RAV4"), Raize("Raize"), Rush("Rush"), Sequoia("Sequoia"), Sienna("Sienna"), Sienta("Sienta"),
    Solara("Solara"), Supra("Supra"), Tacoma("Tacoma"), Tundra("Tundra"), Urban_Cruiser("Urban Cruiser"), Venza("Venza"),
    Verso("Verso"), Vitz("Vitz"), Xa("Xa"), Yaris("Yaris"), Yaris_Cross("Yaris Cross"), Yaris_iA("Yaris iA"), bZ3("bZ3"),
    bZ4X("bZ4X");

    private final String model;

    Toyota(String model) {
        this.model = model;
    }
}
