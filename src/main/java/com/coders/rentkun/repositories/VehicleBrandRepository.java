package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.vehicles.VehicleBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleBrandRepository extends JpaRepository<VehicleBrand, Long> {
    Optional<VehicleBrand> findByName(String brandName);
}
