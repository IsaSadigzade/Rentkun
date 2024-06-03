package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum CarBrands {
    ALFA_ROMEO("Alfa Romeo"),
    ASTON_MARTIN("Aston Martin"),
    AUDI("Audi"),
    BENTLEY("Bentley"),
    BMW("BMW"),
    CADILLAC("Cadillac"),
    CHANGAN("Changan"),
    CHEVROLET("Chevrolet"),
    CITROEN("Citroen"),
    FERRARI("Ferrari"),
    FORD("Ford"),
    HONDA("Honda"),
    HYUNDAI("Hyundai"),
    INFINITI("Infiniti"),
    JAGUAR("Jaguar"),
    JEEP("Jeep"),
    KIA("Kia"),
    LADA_VAZ("Lada Vaz"),
    LAMBORGHINI("Lamborghini"),
    LAND_ROVER("Land Rover"),
    LEXUS("Lexus"),
    MASERATI("Maserati"),
    MAZDA("Mazda"),
    MERCEDES("Mercedes"),
    MERCEDES_MAYBACH("Mercedes Maybach"),
    MINI("Mini"),
    MITSUBISHI("Mitsubishi"),
    NISSAN("Nissan"),
    OPEL("Opel"),
    PEUGEOT("Peugeot"),
    PORSCHE("Porsche"),
    RENAULT("Renault"),
    ROLLS_ROYCE("Rolls Royce"),
    SEAT("Seat"),
    SKODA("Skoda"),
    SUBARU("Subaru"),
    TESLA("Tesla"),
    TOYOTA("Toyota"),
    VOLKSWAGEN("Volkswagen"),
    VOLVO("Volvo");

    private final String brandName;

    CarBrands(String brandName) {
        this.brandName = brandName;
    }
}
