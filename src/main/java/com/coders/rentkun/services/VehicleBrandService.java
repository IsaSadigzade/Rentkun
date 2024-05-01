package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.requests.CreateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleBrand;

import java.util.List;

public interface VehicleBrandService {
    BrandResponseDto saveBrand(CreateBrandRequestDto brandRequestDto);

    List<BrandResponseDto> getAllBrands();

    BrandResponseDto getBrandByBrandId(Long brandId);

    BrandResponseDto getBrandByBrandName(String brandName);

    BrandResponseDto updateBrand(Long brandId, UpdateBrandRequestDto brandRequestDto);

    void deleteBrand(Long brandId);

    void deactivateBrand(String brandId);

    void activateBrand(String brandName);
}
