package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.vehicles.VehicleGearboxType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleGearboxTypeRepository extends JpaRepository<VehicleGearboxType, Long> {
    Optional<VehicleGearboxType> findByName(String gearboxTypeName);
}
