package com.coders.rentkun.controllers;

import com.coders.rentkun.core.utilities.results.DataResult;
import com.coders.rentkun.core.utilities.results.SuccessDataResult;
import com.coders.rentkun.dtos.vehicles.responses.VehicleLogoResponseDto;
import com.coders.rentkun.entities.vehicles.VehicleLogo;
import com.coders.rentkun.services.VehicleLogoService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicle/logo")
public class VehicleLogoController {
    private final VehicleLogoService vehicleLogoService;

    public VehicleLogoController(VehicleLogoService vehicleLogoService) {
        this.vehicleLogoService = vehicleLogoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<DataResult<VehicleLogoResponseDto>> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessDataResult<>(vehicleLogoService.save(file),"File uploaded successfully"));
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

//    @GetMapping("/files")
//    public ResponseEntity<List<VehicleLogo>> getListFiles() {
//        List<VehicleLogo> fileInfos = vehicleLogoService.loadAll()
//                .map(path -> {
//                    String filename = path.getFileName().toString();
//                    String url = MvcUriComponentsBuilder
//                            .fromMethodName(VehicleLogoController.class, "getFile", filename).build().toString();
//
//                    return new VehicleLogo(filename, url);
//                }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
//    }



//    @PostMapping("/upload")
//    public ResponseEntity<?> saveVehicleLogo(@RequestBody CreateVehicleDetailsRequestDto vehicleDetailsRequestDto) {
//        return ResponseEntity.ok(vehicleLogoService.saveVehicleLogo(vehicleDetailsRequestDto));
//    }
//
//    @GetMapping("/download")
//    public ResponseEntity<Set<VehicleDetailsResponseDto>> getAllVehicleLogos() {
//        return ResponseEntity.ok(vehicleLogoService.getAllVehicleLogos());
//    }
//
//    @GetMapping("/download/{vehicleLogoId}")
//    public ResponseEntity<VehicleDetailsResponseDto> getVehicleLogoByVehicleLogoId(@PathVariable Long vehicleLogoId) {
//        return ResponseEntity.ok(vehicleLogoService.getVehicleLogoByVehicleLogoId(vehicleLogoId));
//    }
//
//    @PutMapping("/{vehicleLogoId}")
//    public ResponseEntity<VehicleDetailsResponseDto> updateVehicleLogo(@PathVariable Long vehicleLogoId, @RequestBody UpdateVehicleDetailsRequestDto vehicleDetailsRequestDto) {
//        return ResponseEntity.ok(vehicleLogoService.updateVehicleLogo(vehicleLogoId, vehicleDetailsRequestDto));
//    }
//
//    @DeleteMapping("/{vehicleLogoId}")
//    public ResponseEntity<Void> deleteVehicleLogo(@PathVariable Long vehicleLogoId) {
//        vehicleLogoService.deleteVehicleLogo(vehicleLogoId);
//        return ResponseEntity.ok().build();
//    }
}
