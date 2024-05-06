package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.responses.LogoResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VehicleLogoService {
    LogoResponseDto save(MultipartFile file);

    List<LogoResponseDto> getLogos();

    LogoResponseDto getLogoResponseById(Long vehicleLogoId);

    byte[] getLogoById(Long vehicleLogoId);

    byte[] getLogoByFileName(String filename);

    Resource download(String filename);

    void deleteVehicleLogo(Long vehicleLogoId);
}
