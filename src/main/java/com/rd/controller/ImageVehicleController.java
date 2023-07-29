package com.rd.controller;

import com.rd.service.impl.ImageVehicleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/image")
@RestController
@RequiredArgsConstructor
public class ImageVehicleController {
    private final ImageVehicleServiceImpl imageVehicleService;

    @PostMapping("/vehicle/{vehicleId}")
    public String uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Integer vehicleId) throws IOException {
        return imageVehicleService.uploadImage(file, vehicleId);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<byte[]> getImages(@PathVariable Integer vehicleId){
        return imageVehicleService.downloadImage(vehicleId);
    }
}
