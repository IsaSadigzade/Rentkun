package com.coders.rentkun.dtos.vehicles.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDetailsResponseDto {
    private Long id;
    private String city;
    private String country;
    private String plateNumber;
    private String numberOfSeats;
    private String distance;
    private String color;
    private String year;
    private String description;
}
