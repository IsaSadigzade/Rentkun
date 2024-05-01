package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.vehicles.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {
}
