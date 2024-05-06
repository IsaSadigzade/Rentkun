package com.coders.rentkun.entities.vehicles;

import com.coders.rentkun.enums.common.RentalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private boolean available = true;

//    @Enumerated(EnumType.STRING)
//    private RentalStatus rentalStatus;

    //TODO: These dates for advert
//    private LocalDate availableFromDate;
//    private LocalDate availableToDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private VehicleDetails vehicleDetails;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private VehicleBrand vehicleBrand;

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private VehicleModel vehicleModel;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private VehicleType vehicleType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "vehicle")
    private Set<VehicleFeature> vehicleFeatures;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private VehicleGearboxType vehicleGearboxType;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private VehicleFuelType vehicleFuelType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "vehicle")
    private Set<VehicleImages> vehicleImages;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private VehicleLogo vehicleLogo;
}
