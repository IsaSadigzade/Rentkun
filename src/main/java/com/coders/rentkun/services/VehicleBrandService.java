package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.requests.CreateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandResponseDto;

import java.util.List;

public interface VehicleBrandService {
    BrandResponseDto saveBrand(CreateBrandRequestDto brandRequestDto);

    List<BrandResponseDto> getAllBrands();

    BrandResponseDto getBrandByBrandId(Long brandId);

    BrandResponseDto getBrandByBrandName(String brandName);

    BrandResponseDto updateBrand(Long brandId, UpdateBrandRequestDto brandRequestDto);

    void deleteBrand(Long brandId);

    void deactivateBrandByBrandId(Long brandId);

    void deactivateBrandByBrandName(String brandName);

    void activateBrandByBrandId(Long brandId);

    void activateBrandByBrandName(String brandName);
}
