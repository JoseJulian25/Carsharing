package com.rd.controller;

import com.rd.entity.ImageVehicle;
import com.rd.service.ImageVehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/image")
@Tag(name = "ImageVehicle Controller", description = "Endpoints for managing vehicle images")
@RestController
@RequiredArgsConstructor
public class ImageVehicleController {
    private final ImageVehicleService imageVehicleService;

    @Operation(summary = "Upload images for a vehicle",
            description = "The images need to be .img, .png, .jpeg <br> Requires role: ADMIN")
    @Secured("ADMIN")
    @PostMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<String>> uploadImage(@RequestParam("image") List<MultipartFile> files, @PathVariable Integer vehicleId) throws IOException {
        return ResponseEntity.ok(imageVehicleService.uploadImage(files, vehicleId));
    }

    @Operation(summary = "Get images by Vehicle ID",
            description = "Each image is converted to Byte64. <br> Requires rol: ADMIN")
    @Secured("ADMIN")
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ImageVehicle>> getImages(@PathVariable Integer vehicleId){
        return ResponseEntity.ok(imageVehicleService.downloadImage(vehicleId));
    }

    @Operation(summary = "Delete an image", description = "Requires role: ADMIN")
    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Integer id){
        return ResponseEntity.ok(imageVehicleService.deleteImage(id));
    }
}
