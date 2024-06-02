package com.coders.rentkun.services.vehicles;

import com.coders.rentkun.dtos.vehicles.converts.BrandDtoConverter;
import com.coders.rentkun.dtos.vehicles.requests.CreateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.requests.UpdateBrandRequestDto;
import com.coders.rentkun.dtos.vehicles.responses.BrandResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleBrand;
import com.coders.rentkun.exception.BrandDoesNotExistException;
import com.coders.rentkun.exception.BrandNotFoundException;
import com.coders.rentkun.repositories.vehicles.VehicleBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
 public class VehicleBrandService {
    private final VehicleBrandRepository vehicleBrandRepository;
    private final BrandDtoConverter brandDtoConverter;

    public VehicleBrandService(VehicleBrandRepository vehicleBrandRepository, BrandDtoConverter brandDtoConverter) {
        this.vehicleBrandRepository = vehicleBrandRepository;
        this.brandDtoConverter = brandDtoConverter;
    }

    public BrandResponseDto saveBrand(CreateBrandRequestDto brandRequestDto) {
        return brandDtoConverter.convertToResponse(
                vehicleBrandRepository.save(
                        brandDtoConverter.convertFromRequestToEntity(brandRequestDto)
                )
        );
    }

    public List<BrandResponseDto> getAllBrands() {
        return vehicleBrandRepository.findAll().stream()
                .map(brandDtoConverter::convertToResponse)
                .collect(Collectors.toList());
    }

    public BrandResponseDto getBrandByBrandId(Long brandId) {
        return brandDtoConverter.convertToResponse(findBrandById(brandId));
    }

    public BrandResponseDto getBrandByBrandName(String brandName) {
        VehicleBrand brand = findBrandByBrandName(brandName);
        return brandDtoConverter.convertToResponse(brand);
    }

    public BrandResponseDto updateBrand(Long brandId, UpdateBrandRequestDto brandRequestDto) {
        VehicleBrand brand = findBrandById(brandId);
        return brandDtoConverter.convertToResponse(
                vehicleBrandRepository.save(
                        brandDtoConverter.convertFromRequestToEntity(brand, brandRequestDto)
                )
        );
    }

    public void deleteBrand(Long brandId) {
        if (!isBrandExist(brandId)) {
            vehicleBrandRepository.deleteById(brandId);
        } else {
            throw new BrandDoesNotExistException("Brand doesn't exist by following brandId: " + brandId);
        }
    }

    public void deactivateBrandByBrandId(Long brandId) {
        VehicleBrand brand = findBrandById(brandId);
        brand.setActive(false);
        vehicleBrandRepository.save(brand);
    }

    public void deactivateBrandByBrandName(String brandName) {
        VehicleBrand brand = findBrandByBrandName(brandName);
        brand.setActive(false);
        vehicleBrandRepository.save(brand);
    }

    public void activateBrandByBrandId(Long brandId) {
        VehicleBrand brand = findBrandById(brandId);
        brand.setActive(true);
        vehicleBrandRepository.save(brand);
    }

    public void activateBrandByBrandName(String brandName) {
        VehicleBrand brand = findBrandByBrandName(brandName);
        brand.setActive(true);
        vehicleBrandRepository.save(brand);
    }

    protected VehicleBrand findBrandById(Long brandId) {
        return vehicleBrandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand couldn't be found by following id: " + brandId));
    }

    protected VehicleBrand findBrandByBrandName(String brandName) {
        return vehicleBrandRepository.findByName(brandName)
                .orElseThrow(() -> new BrandNotFoundException("Brand couldn't be found by following brandName: " + brandName));
    }

    protected boolean isBrandExist(Long brandId) {
        return vehicleBrandRepository.existsById(brandId);
    }
}
