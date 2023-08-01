package com.rd.controller;

import com.rd.entity.ImageVehicle;
import com.rd.service.ImageVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/image")
@RestController
@RequiredArgsConstructor
public class ImageVehicleController {
    private final ImageVehicleService imageVehicleService;

    @PostMapping("/vehicle/{vehicleId}")
    public List<String> uploadImage(@RequestParam("image") List<MultipartFile> files, @PathVariable Integer vehicleId) throws IOException {
        return imageVehicleService.uploadImage(files, vehicleId);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<ImageVehicle> getImages(@PathVariable Integer vehicleId){
        return imageVehicleService.downloadImage(vehicleId);
    }

    @DeleteMapping("/{id}")
    public String deleteImage(@PathVariable Integer id){
        return imageVehicleService.deleteImage(id);
    }
}
