package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.vehicles.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, Long> {
}
