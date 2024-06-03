package com.coders.rentkun.repositories.vehicles;

import com.coders.rentkun.entities.vehicles.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {
    Optional<VehicleModel> findByName(String modelName);

    List<VehicleModel> findByVehicleBrandId(Long brandId);

    List<VehicleModel> findByVehicleBrandName(String brandName);

    boolean existsByName(String model);
}
