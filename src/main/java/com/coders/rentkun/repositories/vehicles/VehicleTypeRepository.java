package com.coders.rentkun.repositories.vehicles;

import com.coders.rentkun.entities.vehicles.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
    Optional<VehicleType> findByName(String fuelTypeName);

    boolean existsByName(String typeName);
}
