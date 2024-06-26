package com.coders.rentkun.entities.vehicles;

import com.coders.rentkun.entities.common.Advertiser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_details_id")
    private VehicleDetails vehicleDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_brand_id")
    private VehicleBrand vehicleBrand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_model_id")
    private VehicleModel vehicleModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_gearbox_type_id")
    private VehicleGearboxType vehicleGearboxType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_fuel_type_id")
    private VehicleFuelType vehicleFuelType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_logo_id")
    private VehicleLogo vehicleLogo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "vehicle_feature_mapping",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    private Set<VehicleFeature> vehicleFeatures;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "vehicle_images_mapping",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_images_id")
    )
    private Set<VehicleImages> vehicleImages;

    @OneToOne(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Advertiser advertiser;

    public Vehicle(VehicleDetails vehicleDetails,
                   VehicleBrand vehicleBrand,
                   VehicleModel vehicleModel,
                   VehicleType vehicleType,
                   VehicleGearboxType vehicleGearboxType,
                   VehicleFuelType vehicleFuelType,
                   VehicleLogo vehicleLogo,
                   Set<VehicleFeature> vehicleFeatures) {
        this.vehicleDetails = vehicleDetails;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.vehicleGearboxType = vehicleGearboxType;
        this.vehicleFuelType = vehicleFuelType;
        this.vehicleLogo = vehicleLogo;
        this.vehicleFeatures = vehicleFeatures;
    }
}
