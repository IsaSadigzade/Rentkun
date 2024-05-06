package com.coders.rentkun.dtos.vehicles.converts;

import com.coders.rentkun.dtos.vehicles.requests.CreateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleBrand;
import org.springframework.stereotype.Component;

@Component
public class BrandDtoConverter {

    public BrandResponseDto convertToResponse(VehicleBrand entity) {
        return new BrandResponseDto(entity.getId(), entity.getName());
    }

    public VehicleBrand convertFromRequestToEntity(CreateBrandRequestDto brandRequestDto) {
        VehicleBrand brand = new VehicleBrand();
        brand.setName(brandRequestDto.getBrandName());
        return brand;
    }

    public VehicleBrand convertFromRequestToEntity(VehicleBrand foundBrand, UpdateBrandRequestDto brandRequestDto) {
        foundBrand.setName(brandRequestDto.getBrandName());
        return foundBrand;
    }
}
