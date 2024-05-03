package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.vehicles.VehicleFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleFeaturesRepository extends JpaRepository<VehicleFeature, Long> {
    Optional<VehicleFeature> findByName(String featureName);
}
