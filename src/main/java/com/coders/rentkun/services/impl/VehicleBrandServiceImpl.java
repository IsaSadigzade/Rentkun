package com.coders.rentkun.services.impl;

import com.coders.rentkun.dtos.vehicles.converts.BrandDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleBrand;
import com.coders.rentkun.exception.BrandNotFoundException;
import com.coders.rentkun.repositories.VehicleBrandRepository;
import com.coders.rentkun.services.VehicleBrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
 public class VehicleBrandServiceImpl implements VehicleBrandService {
    private final VehicleBrandRepository vehicleBrandRepository;
    private final BrandDtoConverter brandDtoConverter;

    public VehicleBrandServiceImpl(VehicleBrandRepository vehicleBrandRepository, BrandDtoConverter brandDtoConverter) {
        this.vehicleBrandRepository = vehicleBrandRepository;
        this.brandDtoConverter = brandDtoConverter;
    }

    @Override
    public BrandResponseDto saveBrand(CreateBrandRequestDto brandRequestDto) {
        return brandDtoConverter.convertFromEntityToDto(
                vehicleBrandRepository.save(
                        brandDtoConverter.convertFromRequestToEntity(brandRequestDto)
                )
        );
    }

    @Override
    public List<BrandResponseDto> getAllBrands() {
        return vehicleBrandRepository.findAll().stream().map(brandDtoConverter::convertFromEntityToDto).collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto getBrandByBrandId(Long brandId) {
        VehicleBrand brand = findBrandById(brandId);
        return brandDtoConverter.convertFromEntityToDto(brand);
    }

    @Override
    public BrandResponseDto getBrandByBrandName(String brandName) {
        VehicleBrand brand = findBrandByBrandName(brandName);
        return brandDtoConverter.convertFromEntityToDto(brand);
    }

    @Override
    public BrandResponseDto updateBrand(Long brandId, UpdateBrandRequestDto brandRequestDto) {
        VehicleBrand brand = findBrandById(brandId);
        return brandDtoConverter.convertFromEntityToDto(
                vehicleBrandRepository.save(
                        brandDtoConverter.convertFromRequestToEntity(brand, brandRequestDto)
                )
        );
    }

    @Override
    public void deleteBrand(Long brandId) {
        VehicleBrand brand = findBrandById(brandId);
        vehicleBrandRepository.delete(brand);
    }

    @Override
    public void deactivateBrand(String brandName) {
        VehicleBrand brand = findBrandByBrandName(brandName);
        brand.setActive(false);
        vehicleBrandRepository.save(brand);
    }

    @Override
    public void activateBrand(String brandName) {
        VehicleBrand brand = findBrandByBrandName(brandName);
        brand.setActive(true);
        vehicleBrandRepository.save(brand);
    }

    public VehicleBrand findBrandById(Long brandId) {
        return vehicleBrandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand couldn't be found by following id: " + brandId));
    }

    public VehicleBrand findBrandByBrandName(String brandName) {
        return vehicleBrandRepository.findByName(brandName)
                .orElseThrow(() -> new BrandNotFoundException("Brand couldn't be found by following brandName: " + brandName));
    }
}
