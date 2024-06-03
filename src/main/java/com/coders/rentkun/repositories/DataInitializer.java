package com.coders.rentkun.repositories;

import com.coders.rentkun.entities.users.Authority;
import com.coders.rentkun.entities.users.Role;
import com.coders.rentkun.entities.users.enums.EAuthority;
import com.coders.rentkun.entities.users.enums.ERole;
import com.coders.rentkun.entities.vehicles.*;
import com.coders.rentkun.entities.vehicles.enums.features.EFeature;
import com.coders.rentkun.entities.vehicles.enums.features.EFuelType;
import com.coders.rentkun.entities.vehicles.enums.features.EGearboxType;
import com.coders.rentkun.entities.vehicles.enums.features.EVehicleType;
import com.coders.rentkun.entities.vehicles.enums.models.CarBrands;
import com.coders.rentkun.entities.vehicles.enums.models.*;
import com.coders.rentkun.repositories.users.AuthorityRepository;
import com.coders.rentkun.repositories.users.RoleRepository;
import com.coders.rentkun.repositories.vehicles.*;
import com.coders.rentkun.services.vehicles.VehicleBrandService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepository,
                           AuthorityRepository authorityRepository,
                           VehicleBrandService brandService,
                           VehicleBrandRepository brandRepository,
                           VehicleModelRepository modelRepository,
                           VehicleFeaturesRepository vehicleFeaturesRepository, VehicleFuelTypeRepository vehicleFuelTypeRepository, VehicleGearboxTypeRepository vehicleGearboxTypeRepository, VehicleTypeRepository vehicleTypeRepository) {
        return args -> {
            Arrays.stream(ERole.values()).forEach(role -> {
                if (!roleRepository.existsByName(role)) {
                    roleRepository.save(new Role(null, role));
                }
            });

            Arrays.stream(EAuthority.values()).forEach(authority -> {
                if (!authorityRepository.existsByName(authority)) {
                    authorityRepository.save(new Authority(null, authority));
                }
            });

            Arrays.stream(CarBrands.values()).forEach(carBrand -> {
                if (!brandRepository.existsByName(carBrand.getBrandName())) {
                    brandRepository.save(new VehicleBrand(carBrand.getBrandName()));
                }
            });
            Arrays.stream(AlfaRomeo.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Alfa Romeo")));
                }
            });
            Arrays.stream(AstonMartin.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Aston Martin")));
                }
            });
            Arrays.stream(Audi.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Audi")));
                }
            });
            Arrays.stream(Bentley.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Bentley")));
                }
            });
            Arrays.stream(BMW.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("BMW")));
                }
            });
            Arrays.stream(Cadillac.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Cadillac")));
                }
            });
            Arrays.stream(Changan.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Changan")));
                }
            });
            Arrays.stream(Chevrolet.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Chevrolet")));
                }
            });
            Arrays.stream(Citroen.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Citroen")));
                }
            });
            Arrays.stream(Ferrari.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Ferrari")));
                }
            });
            Arrays.stream(Ford.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Ford")));
                }
            });
            Arrays.stream(Honda.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Honda")));
                }
            });
            Arrays.stream(Hyundai.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Hyundai")));
                }
            });
            Arrays.stream(Infiniti.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.name())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.name(), brandService.findBrandByBrandName("Infiniti")));
                }
            });
            Arrays.stream(Jaguar.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Jaguar")));
                }
            });
            Arrays.stream(Jeep.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Jeep")));
                }
            });
            Arrays.stream(Kia.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Kia")));
                }
            });
            Arrays.stream(Lada.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Lada Vaz")));
                }
            });
            Arrays.stream(Lamborghini.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.name())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.name(), brandService.findBrandByBrandName("Lamborghini")));
                }
            });
            Arrays.stream(LandRover.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Land Rover")));
                }
            });
            Arrays.stream(Lexus.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Lexus")));
                }
            });
            Arrays.stream(Maserati.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Maserati")));
                }
            });
            Arrays.stream(Mazda.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Mazda")));
                }
            });
            Arrays.stream(Mercedes.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Mercedes")));
                }
            });
            Arrays.stream(MercedesMaybach.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Mercedes Maybach")));
                }
            });
            Arrays.stream(Mini.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Mini")));
                }
            });
            Arrays.stream(Mitsubishi.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Mitsubishi")));
                }
            });
            Arrays.stream(Nissan.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Nissan")));
                }
            });
            Arrays.stream(Opel.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Opel")));
                }
            });
            Arrays.stream(Peugeot.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Peugeot")));
                }
            });
            Arrays.stream(Porsche.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Porsche")));
                }
            });
            Arrays.stream(Renault.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Renault")));
                }
            });
            Arrays.stream(RollsRoyce.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Rolls Royce")));
                }
            });
            Arrays.stream(Seat.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Seat")));
                }
            });
            Arrays.stream(Skoda.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.name())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.name(), brandService.findBrandByBrandName("Skoda")));
                }
            });
            Arrays.stream(Subaru.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Subaru")));
                }
            });
            Arrays.stream(Tesla.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Tesla")));
                }
            });
            Arrays.stream(Toyota.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Toyota")));
                }
            });
            Arrays.stream(Volkswagen.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Volkswagen")));
                }
            });
            Arrays.stream(Volvo.values()).forEach(alfaRomeo -> {
                if (!modelRepository.existsByName(alfaRomeo.getModel())) {
                    modelRepository.save(new VehicleModel(alfaRomeo.getModel(), brandService.findBrandByBrandName("Volvo")));
                }
            });
            Arrays.stream(CarBrands.values()).forEach(carBrand -> {
                if (!brandRepository.existsByName(carBrand.getBrandName())) {
                    brandRepository.save(new VehicleBrand(carBrand.getBrandName()));
                }
            });
            Arrays.stream(EFeature.values()).forEach(eFeature -> {
                if (!vehicleFeaturesRepository.existsByName(eFeature.getFeatureName())) {
                    vehicleFeaturesRepository.save(new VehicleFeature(eFeature.getFeatureName()));
                }
            });
            Arrays.stream(EFuelType.values()).forEach(eFuelType -> {
                if (!vehicleFuelTypeRepository.existsByName(eFuelType.getFuelTypeName())) {
                    vehicleFuelTypeRepository.save(new VehicleFuelType(eFuelType.getFuelTypeName()));
                }
            });
            Arrays.stream(EGearboxType.values()).forEach(eGearboxType -> {
                if (!vehicleGearboxTypeRepository.existsByName(eGearboxType.getDisplayName())) {
                    vehicleGearboxTypeRepository.save(new VehicleGearboxType(eGearboxType.getDisplayName()));
                }
            });
            Arrays.stream(EVehicleType.values()).forEach(eVehicleType -> {
                if (!vehicleTypeRepository.existsByName(eVehicleType.getTypeName())) {
                    vehicleTypeRepository.save(new VehicleType(eVehicleType.getTypeName()));
                }
            });
        };
    }
}

