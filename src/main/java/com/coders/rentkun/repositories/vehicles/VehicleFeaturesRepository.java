package com.coders.rentkun.repositories.vehicles;

import com.coders.rentkun.entities.vehicles.VehicleFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface VehicleFeaturesRepository extends JpaRepository<VehicleFeature, Long> {
    Optional<VehicleFeature> findByName(String featureName);

    Set<VehicleFeature> findByIdIn(Set<Long> featureIds);

    boolean existsByName(String featureName);
}
