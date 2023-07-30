package com.rd.service.impl;

import com.rd.entity.ImageVehicle;
import com.rd.entity.Vehicle;
import com.rd.repository.ImageVehicleRepository;
import com.rd.repository.VehicleRepository;
import com.rd.service.ImageVehicleService;
import com.rd.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageVehicleServiceImpl implements ImageVehicleService {
    private final ImageVehicleRepository imageVehicleRepository;
    private final VehicleRepository vehicleRepository;

    @Transactional
    @Override
    public List<String> uploadImage(List<MultipartFile> files, Integer vehicleId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new IllegalArgumentException("vehicle not found exception"));
        List<String> imageSavedSuccessful = new ArrayList<>();

        files.forEach(file -> {
            try {
                imageVehicleRepository.save(ImageVehicle.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imageData(ImageUtils.compressImage(file.getBytes()))
                        .vehicle(vehicle).build());
                imageSavedSuccessful.add("file uploaded successfully : " + file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return imageSavedSuccessful;
    }

    @Transactional
    @Override
    public List<ImageVehicle> downloadImage(Integer vehicleId) {
        List<ImageVehicle> imagesVehicles = imageVehicleRepository.findByVehicle_Id(vehicleId);
        return ImageUtils.decompressImage(imagesVehicles);
    }
}
