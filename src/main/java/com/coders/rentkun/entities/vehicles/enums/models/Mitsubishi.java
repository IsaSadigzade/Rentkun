package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Mitsubishi {
    ASX("ASX"), Airtrek("Airtrek"), Attrage("Attrage"),
    Canter("Canter"), Carisma("Carisma"), Chariot("Chariot"),
    Eclipse("Eclipse"), Eclipse_Cross("Eclipse Cross"),
    Galant("Galant"), Grandis("Grandis"),
    L200("L200"), Lancer("Lancer"),
    Mirage("Mirage"), Montero("Montero"), Montero_Sport("Montero Sport"),
    Nativa("Nativa"),
    Outlander("Outlander"), Outlander_Sport("Outlander Sport"),
    Pajero("Pajero"), Pajero_Pinin("Pajero Pinin"), Pajero_Sport("Pajero Sport"), Pajero_io("Pajero io"),
    Rosa("Rosa"),
    Space_Wagon("Space Wagon"),
    Xpander("Xpander");

    private final String model;

    Mitsubishi(String model) {
        this.model = model;
    }
}
