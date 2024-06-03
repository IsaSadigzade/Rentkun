package com.coders.rentkun.repositories.vehicles;

import com.coders.rentkun.entities.vehicles.VehicleFuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleFuelTypeRepository extends JpaRepository<VehicleFuelType, Long> {
    Optional<VehicleFuelType> findByName(String fuelTypeName);

    boolean existsByName(String fuelTypeName);
}
