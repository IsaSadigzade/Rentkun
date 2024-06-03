package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Nissan {
    Almera("Almera"), Altima("Altima"), Ariya("Ariya"), Armada("Armada"),
    Cedric("Cedric"),
    GT_R("GT-R"), Gloria("Gloria"),
    Juke("Juke"),
    Kicks("Kicks"),
    Latio("Latio"), Leaf("Leaf"),
    March("March"), Maxima("Maxima"), Micra("Micra"), Murano("Murano"),
    Navara("Navara"), Note("Note"),
    Pathfinder("Pathfinder"), Patrol("Patrol"), Presage("Presage"), Primera("Primera"),
    Qashqai("Qashqai"), Qashqai_2("Qashqai 2"),
    Sentra("Sentra"), Serena("Serena"), Skyline("Skyline"), Sunny("Sunny"), Sunny_RZ_1("Sunny RZ-1"),
    Teana("Teana"), Terrano("Terrano"), Tiida("Tiida"),
    Urvan("Urvan"),
    Vanette("Vanette"), Versa("Versa"),
    Wingroad("Wingroad"),
    X_Trail("X-Trail"), Xterra("Xterra");

    private final String model;

    Nissan(String model) {
        this.model = model;
    }
}
