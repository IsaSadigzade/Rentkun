package com.coders.rentkun.services;

import com.coders.rentkun.dtos.vehicles.responses.VehicleLogoResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface VehicleLogoService {
    VehicleLogoResponseDto save(MultipartFile file);

    byte[] getLogoByFileName(String filename);

    Resource download(String filename);

//    public Stream<Path> loadAll();
//
//    public void deleteAll();



}
