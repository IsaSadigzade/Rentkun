package com.coders.rentkun.controllers;

import com.coders.rentkun.core.utilities.results.DataResult;
import com.coders.rentkun.core.utilities.results.SuccessDataResult;
import com.coders.rentkun.dtos.vehicles.responses.LogoResponseDto;
import com.coders.rentkun.services.VehicleLogoService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vehicle/logo")
public class VehicleLogoController {
    private final VehicleLogoService vehicleLogoService;

    public VehicleLogoController(VehicleLogoService vehicleLogoService) {
        this.vehicleLogoService = vehicleLogoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<DataResult<LogoResponseDto>> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessDataResult<>(vehicleLogoService.save(file),"File uploaded successfully"));
    }

    @GetMapping()
    public ResponseEntity<List<LogoResponseDto>> getLogos() {
        return ResponseEntity.ok(vehicleLogoService.getLogos());
    }

    @GetMapping("/response-by-id/{vehicleLogoId}")
    public ResponseEntity<LogoResponseDto> getLogoResponseById(@PathVariable Long vehicleLogoId) {
        return ResponseEntity.ok(vehicleLogoService.getLogoResponseById(vehicleLogoId));
    }

    @GetMapping("/id/{vehicleLogoId}")
    @ResponseBody
    public ResponseEntity<byte[]> getLogoById(@PathVariable Long vehicleLogoId) {
        byte[] fileBytes = vehicleLogoService.getLogoById(vehicleLogoId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<byte[]> getLogoByFileName(@PathVariable String filename) {
        byte[] fileBytes = vehicleLogoService.getLogoByFileName(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }


    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> download(@PathVariable String filename) {
        Resource file = vehicleLogoService.download(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/{vehicleLogoId}")
    public ResponseEntity<Void> deleteVehicleLogo(@PathVariable Long vehicleLogoId) {
        vehicleLogoService.deleteVehicleLogo(vehicleLogoId);
        return ResponseEntity.ok().build();
    }
}
