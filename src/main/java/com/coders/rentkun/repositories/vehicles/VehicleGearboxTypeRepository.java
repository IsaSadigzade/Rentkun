package com.coders.rentkun.repositories.vehicles;

import com.coders.rentkun.entities.vehicles.VehicleGearboxType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleGearboxTypeRepository extends JpaRepository<VehicleGearboxType, Long> {
    Optional<VehicleGearboxType> findByName(String gearboxTypeName);

    boolean existsByName(String displayName);
}
