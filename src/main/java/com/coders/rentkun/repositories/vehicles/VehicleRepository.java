package com.coders.rentkun.repositories.vehicles;

import com.coders.rentkun.entities.vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
